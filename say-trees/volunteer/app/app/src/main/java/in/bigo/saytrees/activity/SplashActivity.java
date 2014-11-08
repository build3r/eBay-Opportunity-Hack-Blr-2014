package in.bigo.saytrees.activity;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.location.Address;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;

import java.util.List;

import in.bigo.saytrees.R;
import in.bigo.saytrees.controller.SharedPreferencesController;
import in.bigo.saytrees.utils.ConnectionDetector;
import in.bigo.saytrees.utils.GPSTracker;
import in.bigo.saytrees.utils.UtilityMethods;

public class SplashActivity extends Activity {

    private GoogleMap googleMap;
    private GPSTracker gps;

    private SharedPreferencesController sharedPreferencesController;
    private double latitude;
    private double longitude;

    private final int SPLASH_SCREEN_TIME_OUT = 2000;
    private TextView companyName;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setUpUI();
        sharedPreferencesController = SharedPreferencesController.getSharedPreferencesController(this);

        ConnectionDetector cd = new ConnectionDetector(getApplicationContext());

        Boolean isInternetPresent = cd.isConnectingToInternet();

        if (isInternetPresent) {

            gps = new GPSTracker(this);

            // check if GPS enabled
            if (gps.canGetLocation()) {
                latitude = gps.getLatitude();
                longitude = gps.getLongitude();

                sharedPreferencesController.putString("latitude", String.valueOf(latitude));
                sharedPreferencesController.putString("longitude", String.valueOf(longitude));

                new GetLocation().execute(latitude, longitude);


            } else {

                // can't get location
                // GPS or Network is not enabled
                // Ask user to enable GPS/network in settings
                gps.showSettingsAlert();
            }


            if (sharedPreferencesController.getString("APIKey").equals(""))
                launchLoginActivity();
            else
                launchMainActivity();
        } else {
            Toast.makeText(getApplicationContext(), "No Internet connection", Toast.LENGTH_LONG).show();
        }


        //Key hash generation
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo("in.bigo.cleanindia", PackageManager.GET_SIGNATURES);
//            for (android.content.pm.Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                String sign = Base64.encodeToString(md.digest(), Base64.DEFAULT);
//                Log.e("MY KEY HASH:", sign);
//                //textInstructionsOrLink = (TextView)findViewById(R.id.textstring);
//                //textInstructionsOrLink.setText(sign);
//                Toast.makeText(getApplicationContext(), sign, Toast.LENGTH_LONG).show();
//            }
//        } catch (Exception e) {
//            Log.d("nope", "nope");
//        }

    }

    //UI setup related stuff
    private void setUpUI() {
        companyName = (TextView) findViewById(R.id.company_name);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");
        companyName.setTypeface(tf);
    }


    //Call the main activity
    private void launchLoginActivity() {
        runnable = new Runnable() {
            @Override
            public void run() {
                //Call main activ ity
                Intent i = new Intent(SplashActivity.this, LoginSignUpActivity.class);
                startActivity(i);
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        };

        handler = new Handler();
        handler.postDelayed(runnable, SPLASH_SCREEN_TIME_OUT);
    }


    private void launchMainActivity() {
        runnable = new Runnable() {
            @Override
            public void run() {
                //Call main activ ity
                Intent i = new Intent(SplashActivity.this, LauncherActivity.class);
                startActivity(i);
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        };

        handler = new Handler();
        handler.postDelayed(runnable, SPLASH_SCREEN_TIME_OUT);

    }


    private class GetLocation extends AsyncTask<Double, Void, List<Address>> {
        @Override
        protected List<Address> doInBackground(Double... params) {
            try {
                return UtilityMethods.getStringFromLocation(params[0], params[1]);
            } catch (Exception e) {
                e.printStackTrace();
                ;
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Address> addressList) {

            if (addressList != null && addressList.size() > 0) {
                String location = addressList.get(0).getAddressLine(0).toString();
                sharedPreferencesController.putString("location", location );

            }
        }
    }

}
