package in.bigo.saytrees.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.parse.ParseObject;

import in.bigo.saytrees.R;
import in.bigo.saytrees.activity.LauncherActivity;

/**
 * Created by karthikeyan on 10/11/14.
 */
public class AboutUsFragment extends Fragment {

    //private TextView about_one, about_two, about_three, about_four, about_five;
    WebView webView;
    View rootView;
    LauncherActivity activityAboutUsFragment;
RelativeLayout relativeLayout;
    EditText volunteerName;
    EditText emailAddress;
    EditText phoneNumber;
    Button sendButton;

    public AboutUsFragment() {
    }

    /**
     * Returns a new instance of this fragment
     */
    public static AboutUsFragment newInstance() {
        AboutUsFragment fragment = new AboutUsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_donate, container, false);
        initUI();
        return rootView;
    }


    private void initUI() {

        volunteerName = (EditText) rootView.findViewById(R.id.volname);
        emailAddress = (EditText) rootView.findViewById(R.id.email);
        phoneNumber = (EditText) rootView.findViewById(R.id.phone);
        webView = (WebView) rootView.findViewById(R.id.webView);
        relativeLayout =(RelativeLayout) rootView.findViewById(R.id.layout);
        sendButton = (Button) rootView.findViewById(R.id.sendButt);



        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = volunteerName.getText().toString();
                String mobileNumber = phoneNumber.getText().toString();
                String emailaddr = emailAddress.getText().toString();

                ParseObject dataObject = new ParseObject("saytrees");
                dataObject.put("name", username);
                dataObject.put("mobilenumber", mobileNumber);
                dataObject.put("occupation", emailaddr);
                dataObject.saveInBackground();
                relativeLayout.setVisibility(View.GONE);

                webView.setVisibility(View.VISIBLE);
                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl("http://saytrees.org/Donate.php");
            }
        });




    }


    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            activityAboutUsFragment = ((LauncherActivity) activity);
            activityAboutUsFragment.onSectionAttached(
                    3);
        } catch (Exception e) {
            e.printStackTrace();
            getActivity().finish();
            Intent newIntent = new Intent(getActivity(), LauncherActivity.class);
            startActivity(newIntent);


        }


    }

}
