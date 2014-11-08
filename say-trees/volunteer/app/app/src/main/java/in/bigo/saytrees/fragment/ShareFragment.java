package in.bigo.saytrees.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookRequestError;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphPlace;
import com.facebook.model.GraphUser;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.WebDialog;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import in.bigo.saytrees.R;
import in.bigo.saytrees.activity.LauncherActivity;
import in.bigo.saytrees.adapter.FriendsAdapter;
import in.bigo.saytrees.controller.SharedPreferencesController;
import in.bigo.saytrees.model.TaggableFriends;
import in.bigo.saytrees.model.TaggableFriendsWrapper;


public class ShareFragment extends Fragment implements View.OnClickListener{

    LauncherActivity activityShareFragment;
    private SharedPreferencesController sharedPreferencesController;
    private String accessToken = "";
    Session activeSession;
    private ProgressBar progressBar;
    private ListView listView;
    private FriendsAdapter friendsAdapter;
    String taggable_friends = "";
    private View rootView;
    private UiLifecycleHelper uiHelper;
    TaggableFriendsWrapper taggableFriendsWrapper;
    int textlength = 0;
    private ArrayList<TaggableFriends> filteredFriends = new ArrayList<TaggableFriends>();
    private ArrayList<String> wholeData = new ArrayList<String>();
    private boolean canPresentShareDialog;
    private GraphUser user;
    private GraphPlace place;
    private List<GraphUser> tags;
    private static Location CURRENT_LOCATION = null;
    private PendingAction pendingAction = PendingAction.NONE;
    private String sharingText = "Swachh calls you for action to show you care and clean your share. It is an initiative and a community platform to motivate people towards developing a cleaner India. It helps you to identify and fix the issues buy collaborating with your friends and also socially interested people nearby your place. Get help from people socially through this app https://play.google.com/store/apps/details?id=in.bigo.cleanindia";
    private String sharingTwitterText = "Swachh calls you to clean your share on cleaner India! Connect here socially https://play.google.com/store/apps/details?id=in.bigo.cleanindia";


    RelativeLayout facebookLayout;
    RelativeLayout twitterLayout;
    RelativeLayout whatsappLayout;
    RelativeLayout hikeLayout;
    RelativeLayout othersLayout;


    private enum PendingAction {
        NONE,
        POST_PHOTO,
        POST_STATUS_UPDATE
    }


    public ShareFragment() {
    }

    EditText searchBox;
    List<String> listFriends = new ArrayList<String>();

    /**
     * Returns a new instance of this fragment
     */
    public static ShareFragment newInstance() {
        ShareFragment fragment = new ShareFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_share, container, false);
        initUI();
        getTaggableFriends();
        setHasOptionsMenu(true);
        return rootView;
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        // TODO Auto-generated method stub
//        super.onCreateOptionsMenu(menu, inflater);
//        inflater.inflate(R.menu.launcher, menu);
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiHelper = new UiLifecycleHelper(getActivity(), null);
        uiHelper.onCreate(savedInstanceState);
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // handle item selection
//        switch (item.getItemId()) {
//            case R.id.save:
//                //  Toast.makeText(getActivity(), "yo", Toast.LENGTH_SHORT).show();
//
////                FacebookDialog shareDialog = new FacebookDialog.ShareDialogBuilder(getActivity())
////                        .setLink("http://www.swachh.org")
////                        .setFriends(listFriends)
////                        .build();
////                uiHelper.trackPendingDialogCall(shareDialog.present());
//
//                //publishFeedDialog();
//
//                sendRequestDialog();
//
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }



    void initUI()
    {
        facebookLayout = (RelativeLayout) rootView.findViewById(R.id.share_layout_facebook);
        twitterLayout = (RelativeLayout) rootView.findViewById(R.id.share_layout_twitter);
        whatsappLayout = (RelativeLayout) rootView.findViewById(R.id.share_layout_whatsapp);
        hikeLayout = (RelativeLayout) rootView.findViewById(R.id.share_layout_hike);
        othersLayout = (RelativeLayout) rootView.findViewById(R.id.share_layout_others);

        facebookLayout.setOnClickListener(this);
        twitterLayout.setOnClickListener(this);
        whatsappLayout.setOnClickListener(this);
        hikeLayout.setOnClickListener(this);
        othersLayout.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch(id)
        {
            case R.id.share_layout_facebook:
                publishFeedDialog();
                break;
            case R.id.share_layout_twitter:
                ShareTwitterIntent();
                break;
            case R.id.share_layout_whatsapp:
                ShareWhatsappIntent();
                break;
            case R.id.share_layout_hike:
                ShareHikeIntent();
                break;
            case R.id.share_layout_others:
                shareText();
                break;
        }

    }

    private void sendRequestDialog() {
        Bundle params = new Bundle();
        params.putString("message", "Lets make India a cleaner and stronger country! Join Swachh bharat mission today!");
        params.putString("data", "");

        params.putString("to",
                taggable_friends.substring(1, taggable_friends.length() - 1));


        WebDialog requestsDialog = (
                new WebDialog.RequestsDialogBuilder(getActivity(),
                        Session.getActiveSession(),
                        params))
                .setOnCompleteListener(new WebDialog.OnCompleteListener() {

                    @Override
                    public void onComplete(Bundle values,
                                           FacebookException error) {
                        if (error != null) {
                            if (error instanceof FacebookOperationCanceledException) {
                                Toast.makeText(getActivity().getApplicationContext(),
                                        "Request cancelled",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity().getApplicationContext(),
                                        "Network Error",
                                        Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            final String requestId = values.getString("request");
                            if (requestId != null) {
                                Toast.makeText(getActivity().getApplicationContext(),
                                        "Request sent",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity().getApplicationContext(),
                                        "Request cancelled",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                })
                .build();
        requestsDialog.show();
    }



    void ShareHikeIntent()
    {
        /**
         * Swachh calls you for action to show you care and clean your share. It is an initiative and a community platform to motivate people towards developing a cleaner India. It helps you to identify and fix the issues buy collaborating with your friends and also socially interested people nearby your place. Get help from people socially through this app https://play.google.com/store/apps/details?id=in.bigo.cleanindia
         */
        PackageManager pm= getActivity().getPackageManager();
        try {

            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");
            PackageInfo info= pm.getPackageInfo("com.bsb.hike", PackageManager.GET_META_DATA);
            //Check if package exists or not. If not then code
            //in catch block will be called
            waIntent.setPackage("com.bsb.hike");
            waIntent.putExtra(Intent.EXTRA_TEXT, sharingText);
            startActivity(Intent.createChooser(waIntent, "Share with"));
        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(getActivity(), "Hike not Installed", Toast.LENGTH_SHORT)
                    .show();
            //download for example after dialog
            Uri uri = Uri.parse("market://details?id=com.bsb.hike");
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        }
    }




    void ShareTwitterIntent()
    {
        /**
         * Swachh calls you for action to show you care and clean your share. It is an initiative and a community platform to motivate people towards developing a cleaner India. It helps you to identify and fix the issues buy collaborating with your friends and also socially interested people nearby your place. Get help from people socially through this app https://play.google.com/store/apps/details?id=in.bigo.cleanindia
         */
        PackageManager pm= getActivity().getPackageManager();
        try {

            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");
            PackageInfo info= pm.getPackageInfo("com.twitter.android", PackageManager.GET_META_DATA);
            //Check if package exists or not. If not then code
            //in catch block will be called
            waIntent.setPackage("com.twitter.android");
            waIntent.putExtra(Intent.EXTRA_TEXT, sharingTwitterText);
            startActivity(Intent.createChooser(waIntent, "Share with"));
        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(getActivity(), "Twitter not Installed", Toast.LENGTH_SHORT)
                    .show();
            //download for example after dialog
            Uri uri = Uri.parse("market://details?id=com.twitter.android");
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        }
    }


    void ShareWhatsappIntent()
    {
        /**
         * Swachh calls you for action to show you care and clean your share. It is an initiative and a community platform to motivate people towards developing a cleaner India. It helps you to identify and fix the issues buy collaborating with your friends and also socially interested people nearby your place. Get help from people socially through this app https://play.google.com/store/apps/details?id=in.bigo.cleanindia
         */
        PackageManager pm= getActivity().getPackageManager();
        try {

            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");
            PackageInfo info= pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            //Check if package exists or not. If not then code
            //in catch block will be called
            waIntent.setPackage("com.whatsapp");
            waIntent.putExtra(Intent.EXTRA_TEXT, sharingText);
            startActivity(Intent.createChooser(waIntent, "Share with"));
        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(getActivity(), "WhatsApp not Installed", Toast.LENGTH_SHORT)
                    .show();
            //download for example after dialog
            Uri uri = Uri.parse("market://details?id=com.whatsapp");
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        }
    }

    void shareText()
    {
        final Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Swachh calls you to take action towards clean India!");
        intent.putExtra(Intent.EXTRA_TEXT, sharingText);
        startActivity(Intent.createChooser(intent, getString(R.string.share)));
    }

    private void publishFeedDialog() {
        Bundle params = new Bundle();
        params.putString("name", "Swachh");
        params.putString("tags", taggable_friends);
        params.putString("caption", "Show you care, clean your share");
        params.putString("description", "Swachh is an initiative and platform to motivate people towards developing a clean India. It helps you to identify and fix the issues buy collaborating with your friends and also socially interested people nearby your place.");
        params.putString("link", "https://www.swachh.org");
        params.putString("picture", "https://s3-ap-southeast-1.amazonaws.com/cleanindia/ic_launcher.png");

        WebDialog feedDialog = (
                new WebDialog.FeedDialogBuilder(getActivity(),
                        activeSession,
                        params))
                .setOnCompleteListener(new WebDialog.OnCompleteListener() {

                    @Override
                    public void onComplete(Bundle values,
                                           FacebookException error) {
                        if (error == null) {
                            // When the story is posted, echo the success
                            // and the post Id.
                            final String postId = values.getString("post_id");
                            if (postId != null) {
                                Toast.makeText(getActivity(),
                                        "Posted story, id: " + postId,
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                // User clicked the Cancel button
                                Toast.makeText(getActivity().getApplicationContext(),
                                        "Publish cancelled",
                                        Toast.LENGTH_SHORT).show();
                            }
                        } else if (error instanceof FacebookOperationCanceledException) {
                            // User clicked the "x" button
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Publish cancelled",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // Generic, ex: network error
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Error posting story",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                })
                .build();
        feedDialog.show();
    }


    private void postStatusUpdate() {
        if (canPresentShareDialog) {
            FacebookDialog shareDialog = createShareDialogBuilderForLink().build();
            uiHelper.trackPendingDialogCall(shareDialog.present());
        } else if (user != null && hasPublishPermission()) {
            final String message = getString(R.string.status_update, user.getFirstName(), (new Date().toString()));
            Request request = Request
                    .newStatusUpdateRequest(Session.getActiveSession(), message, place, tags, new Request.Callback() {
                        @Override
                        public void onCompleted(Response response) {
                            showPublishResult(message, response.getGraphObject(), response.getError());
                        }
                    });
            request.executeAsync();
        } else {
            pendingAction = PendingAction.POST_STATUS_UPDATE;
        }
    }

    private void showPublishResult(String message, GraphObject result, FacebookRequestError error) {
        String title = null;
        String alertMessage = null;
        if (error == null) {
            title = getString(R.string.success);
            String id = result.cast(GraphObjectWithId.class).getId();
            alertMessage = getString(R.string.successfully_posted_post, message, id);
        } else {
            title = getString(R.string.error);
            alertMessage = error.getErrorMessage();
        }

        new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(alertMessage)
                .setPositiveButton(R.string.ok, null)
                .show();
    }


    private interface GraphObjectWithId extends GraphObject {
        String getId();
    }

    private boolean hasPublishPermission() {
        Session session = Session.getActiveSession();
        return session != null && session.getPermissions().contains("publish_actions");
    }


    private FacebookDialog.ShareDialogBuilder createShareDialogBuilderForLink() {
        return new FacebookDialog.ShareDialogBuilder(getActivity())
                .setName("Swachh")
                .setPicture("https://s3-ap-southeast-1.amazonaws.com/cleanindia/ic_launcher.png")
                .setDescription("The Swachh team calls you to take an action!")
                .setLink("http://www.swachh.org");
    }




    @Override
    public void onResume() {
        super.onResume();

    }

//    private Drawable getProgressDrawable() {
//        Drawable progressDrawable = new FoldingCirclesDrawable.Builder(getActivity())
//                .colors(getProgressDrawableColors())
//                .build();
//
//        return progressDrawable;
//    }

    public void AddToList(ArrayList<TaggableFriends> friendsWrapper) {
        TaggableFriendsWrapper newWrapper = new TaggableFriendsWrapper();
        newWrapper.setData(friendsWrapper);
        friendsAdapter = new FriendsAdapter(getActivity(), newWrapper);
        listView.setAdapter(friendsAdapter);
    }


    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state,
                         Exception exception) {
            // onSessionStateChange(session, state, exception);
        }
    };

    private int[] getProgressDrawableColors() {
        int[] colors = new int[4];
        colors[0] = getResources().getColor(R.color.flat_orange);
        colors[1] = getResources().getColor(R.color.flat_white);
        colors[2] = getResources().getColor(R.color.flat_green);
        colors[3] = getResources().getColor(R.color.blue);
        return colors;
    }


    public void getTaggableFriends() {
        activeSession = Session.getActiveSession();
        activeSession = Session.openActiveSession(getActivity(), false, callback);
        //  if(activeSession != null)
        //getFriends();


    }


    private void getFriends() {


        Request myRequest = new Request(
                activeSession,
                "/me/taggable_friends",
                null,
                HttpMethod.GET,
                new Request.Callback() {
                    public void onCompleted(com.facebook.Response response) {
                        progressBar.setVisibility(View.GONE);
                        listView.setVisibility(View.VISIBLE);
                        com.facebook.model.GraphObject graphObject = response.getGraphObject();
                        if (graphObject != null) {
                            JSONObject jsonObject = graphObject.getInnerJSONObject();
                            String taggableFriendsJson = jsonObject.toString();
                            //Log.d("print json", taggableFriendsJson);
                            Gson gson = new Gson();
                            taggableFriendsWrapper = gson.fromJson(taggableFriendsJson, TaggableFriendsWrapper.class);
                            friendsAdapter = new FriendsAdapter(getActivity(), taggableFriendsWrapper);
                            listView.setAdapter(friendsAdapter);
                        } else {
                            // do something

                        }
                    }
                }
        );
        myRequest.executeAsync();


    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            activityShareFragment = ((LauncherActivity) activity);
            activityShareFragment.onSectionAttached(
                    6);
        } catch (Exception e) {
            e.printStackTrace();
            getActivity().finish();
            Intent newIntent = new Intent(getActivity(), LauncherActivity.class);
            startActivity(newIntent);

        }
    }

}
