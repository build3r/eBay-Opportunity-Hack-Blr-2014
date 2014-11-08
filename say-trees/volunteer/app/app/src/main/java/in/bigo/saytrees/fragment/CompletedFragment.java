package in.bigo.saytrees.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import in.bigo.saytrees.R;
import in.bigo.saytrees.activity.LauncherActivity;

public class CompletedFragment extends Fragment {


    private View rootView;
LauncherActivity activityCompletedFragment;
    private String TAG = CompletedFragment.class.toString();

    public CompletedFragment() {
    }

    /**
     * Returns a new instance of this fragment
     */
    public static CompletedFragment newInstance() {
        CompletedFragment fragment = new CompletedFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_completed, container, false);
        return rootView;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        this.getActivity().getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



    @Override
    public void onResume() {
        super.onResume();
        /**Dynamically*/
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            activityCompletedFragment = ((LauncherActivity) activity);
            activityCompletedFragment.onSectionAttached(
                    3);
        } catch (Exception e) {
            e.printStackTrace();
            getActivity().finish();
            Intent newIntent = new Intent(getActivity(), LauncherActivity.class);
            startActivity(newIntent);

        }

    }


}
