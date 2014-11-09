package in.bigo.saytrees.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import in.bigo.saytrees.R;
import in.bigo.saytrees.controller.SharedPreferencesController;
import in.bigo.saytrees.utils.AppConstants;
import in.bigo.saytrees.utils.AppController;

public class NotificationsActivity extends Activity {

    TextView dateText;
    TextView eventNameText;
    TextView eventOrganizerText;
    TextView eventAddressText;
    SharedPreferencesController sharedPreferencesController;
    String date;
    String eventId;
    String eventName;
    String eventOrganizer;
    String eventAddress;


    Button join;
    Button decline;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_notifications);

        dateText = (TextView) findViewById(R.id.date);
        eventNameText = (TextView) findViewById(R.id.event_name);
        eventOrganizerText = (TextView) findViewById(R.id.organizer_name);
        eventAddressText = (TextView) findViewById(R.id.location_data);
        sharedPreferencesController = SharedPreferencesController.getSharedPreferencesController(this);

        join= (Button) findViewById(R.id.join_button);
        decline = (Button) findViewById(R.id.decline_button);

        Intent intent = getIntent();

        date = intent.getStringExtra("time");
        eventId = intent.getStringExtra("event_id");
        eventName = intent.getStringExtra("event_name");
        eventOrganizer = intent.getStringExtra("organizedBy");
        eventAddress = intent.getStringExtra("address");

        dateText.setText(date);
        eventNameText.setText(eventName);
        eventOrganizerText.setText(eventOrganizer);
        eventAddressText.setText(eventAddress);

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject requestObj = new JSONObject();
                try {
                    requestObj.put("eventId", eventId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, AppConstants.SERVER_HOST_ADDRESS + AppConstants.JOIN_EVENT, requestObj, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        if (response != null) {

                            Log.d("clicked", response.toString());


                            onBackPressed();
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
                AppController.getInstance(getApplicationContext()).addToRequestQueue(req);

            }
        });


        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });


    }

//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.notifications, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        if(id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onResume()
    {
        super.onResume();
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    }

    protected void onPause()
    {
        super.onPause();
        overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
    }




}
