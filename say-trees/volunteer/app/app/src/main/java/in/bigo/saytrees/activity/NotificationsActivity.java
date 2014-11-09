package in.bigo.saytrees.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import in.bigo.saytrees.R;

public class NotificationsActivity extends Activity {

    TextView dateText;
    TextView eventNameText;
    TextView eventOrganizerText;
    TextView eventAddressText;

    String date;
    int eventId;
    String eventName;
    String eventOrganizer;
    String eventAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_notifications);

        dateText = (TextView) findViewById(R.id.date);
        eventNameText = (TextView) findViewById(R.id.event_name);
        eventOrganizerText = (TextView) findViewById(R.id.organizer_name);
        eventAddressText = (TextView) findViewById(R.id.event_location);

        Intent intent = getIntent();

        date = intent.getStringExtra("time");
        eventId = intent.getIntExtra("event_id", 0);
        eventName = intent.getStringExtra("event_name");
        eventOrganizer = intent.getStringExtra("organizedBy");
        eventAddress = intent.getStringExtra("address");

        dateText.setText(date);
        eventNameText.setText(eventName);
        eventOrganizerText.setText(eventOrganizer);
        eventAddressText.setText(eventAddress);


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
