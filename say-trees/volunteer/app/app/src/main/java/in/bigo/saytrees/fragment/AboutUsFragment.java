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

        webView = (WebView) rootView.findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://test.payu.in/_payment");
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
                    4);
        } catch (Exception e) {
            e.printStackTrace();
            getActivity().finish();
            Intent newIntent = new Intent(getActivity(), LauncherActivity.class);
            startActivity(newIntent);


        }


    }

}
