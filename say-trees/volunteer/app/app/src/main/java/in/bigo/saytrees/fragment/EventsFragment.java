package in.bigo.saytrees.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import in.bigo.saytrees.R;
import in.bigo.saytrees.activity.LauncherActivity;
import in.bigo.saytrees.adapter.EventsAdapter;
import in.bigo.saytrees.controller.SharedPreferencesController;
import in.bigo.saytrees.model.Events;
import in.bigo.saytrees.utils.AppController;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EventsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EventsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    SharedPreferencesController sharedPreferencesController;
    LauncherActivity activityVolunteeringFragment;
    ListView listView;
    ArrayList<Events> eventsArrayList;
    ArrayList<Events> returnedEventsArrayList;
    EventsAdapter adapter;

    View rootView;
    // TODO: Rename and change types of parameters
  //  private String mParam1;
  //  private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment EventsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventsFragment newInstance() {
        EventsFragment fragment = new EventsFragment();
        Bundle args = new Bundle();
       // args.putString(ARG_PARAM1, param1);
       // args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public EventsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
         //   mParam1 = getArguments().getString(ARG_PARAM1);
         //   mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_events, container, false);

        Log.d("json", "json comes here");

        initUI();

        return rootView;
    }

    private void initUI() {
        listView = (ListView) rootView.findViewById(R.id.list);
        eventsArrayList = new ArrayList<Events>();
        returnedEventsArrayList = new ArrayList<Events>();

        sharedPreferencesController = SharedPreferencesController.getSharedPreferencesController(this.getActivity());
        sharedPreferencesController.getSharedPreferencesEditor();

        // making fresh volley request and getting json
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET,"http://swachh.org:4567/api/fetchevents", null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                VolleyLog.d("response", "Response feed: " + response.toString());
                if (response != null) {


                    returnedEventsArrayList = parseJsonFeed(response);


                    if (returnedEventsArrayList != null) {
                        for (int i = 0; i < returnedEventsArrayList.size(); i++) {
                            Log.d("json", "json "+returnedEventsArrayList.get(i).getOrganizer());
                            eventsArrayList.add(returnedEventsArrayList.get(i));
                        }

                        // notify data changes to list adapater
                        //adapter.notifyDataSetChanged();

                        listView.setAdapter(new EventsAdapter(getActivity(), eventsArrayList));


                        // progressBar.setVisibility(View.GONE);
                       // listView.setVisibility(View.VISIBLE);

                        //API_OFFSET += API_LIMIT;
                    }


                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("", "Error: " + error.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> actualHeader = new HashMap<String, String>();
                String apiKey = sharedPreferencesController.getString("APIKey");
                actualHeader.put("APIKey", apiKey);
                return actualHeader;
            }
        };
        // Adding request to volley request queue
        AppController.getInstance(this.getActivity().getApplicationContext()).addToRequestQueue(req);
        // }


    }

    /**
     * saplingCount: "32322",
     organizer: "Mukesh",
     eventTime: "2014-12-06 21:25:07",
     location: "Mumbai",
     timestamp: "1415467012675",
     volunteersCount: 0
     */


    /**
     * Parsing json reponse and passing the data to feed view list adapter
     */
    private ArrayList<Events> parseJsonFeed(JSONObject feedArray) {
        ArrayList<Events> parsedItems = new ArrayList<Events>();
        try {

            int length = feedArray.length();
            if (length == 0) {
                Events item = new Events();
                //item.setName("No Content available");
                return null;
            }

            Iterator iterator = feedArray.keys();
            while (iterator.hasNext()) {
                String id = iterator.next().toString();
                JSONObject feedObj = (JSONObject) feedArray.get(id);

                Events item = new Events();
                item.setId(id);
                item.setEventTime(feedObj.getString("eventTime"));
                item.setSaplingCount(feedObj.getString("saplingCount"));
                item.setOrganizer(feedObj.getString("organizer"));
                item.setLocation(feedObj.getString("address"));
                item.setEventName(feedObj.getString("eventName"));
                item.setTimestamp(feedObj.getString("timestamp"));
                item.setVolunteersCount(feedObj.getInt("volunteersCount"));

                parsedItems.add(item);

            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return parsedItems;
    }


        // TODO: Rename method, update argument and hook method into UI event

//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            activityVolunteeringFragment = ((LauncherActivity) activity);
            activityVolunteeringFragment.onSectionAttached(
                    2);
        } catch (Exception e) {
            e.printStackTrace();
            getActivity().finish();
            Intent newIntent = new Intent(getActivity(), LauncherActivity.class);
            startActivity(newIntent);

        }
//
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
