package in.bigo.saytrees.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.cloudinary.Cloudinary;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.bigo.saytrees.R;
import in.bigo.saytrees.controller.SharedPreferencesController;
import in.bigo.saytrees.utils.AppConstants;
import in.bigo.saytrees.utils.AppController;
import in.bigo.saytrees.utils.CapturePhoto;
import in.bigo.saytrees.utils.ConnectionDetector;
import in.bigo.saytrees.utils.GPSTracker;
import in.bigo.saytrees.utils.UtilityMethods;

public class SwachhPostActivity extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private final int ACTION_REQUEST_GALLERY = 0;
    private final int ACTION_REQUEST_CAMERA = 1;
    private ImageView swachhPostImage;
    private FrameLayout imageLayout;
    private boolean imageSet = false;
    private String mCurrentPhotoPath;
    private TextView description;
    private String selectedSeverity = "MEDIUM";
    private TextView landmark;
    private Cloudinary cloudinary;
    private String cloudinaryImageId;
    CapturePhoto capture;
    SharedPreferencesController sharedPreferencesController;
    private GPSTracker gps;
    private double latitude;
    private double longitude;
    private String location = "";
    private TextView locationData;
    ProgressDialog progressDialog;
    private List<Address> listOfAddress = new ArrayList<Address>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        setContentView(R.layout.activity_swachh_post);
        getLocation();
        initUI();
    }


    private void getLocation() {

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
            } else {
                // can't get location
                // GPS or Network is not enabled
                // Ask user to enable GPS/network in settings
                gps.showSettingsAlert();
            }

        } else {
            Toast.makeText(getApplicationContext(), "No Internet connection", Toast.LENGTH_LONG).show();
        }


        new GetLocation().execute(latitude, longitude);

    }




    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        switch (pos) {
            case 0:
                selectedSeverity = "HIGH";
                break;
            case 1:
                selectedSeverity = "MEDIUM";
                break;
            case 2:
                selectedSeverity = "LOW";
                break;
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }


    private void initUI() {

        //Cloudinary
        Map config = new HashMap();
        config.put("cloud_name", "bigo");
        config.put("api_key", "374235891142379");
        config.put("api_secret", "nmGCjZVElJrQSZsrp-MWVDmaXJk");
        cloudinary = new Cloudinary(config);
        capture = new CapturePhoto(this);
        sharedPreferencesController = SharedPreferencesController.getSharedPreferencesController(this);
        swachhPostImage = (ImageView) findViewById(R.id.swachh_photo);
        imageLayout = (FrameLayout) findViewById(R.id.swachh_photo_layout);
        locationData = (TextView) findViewById(R.id.location_data);
        locationData.setSelected(true);
        imageLayout.setOnClickListener(this);

        selectImage(R.id.swachh_photo);
        description = (TextView) findViewById(R.id.description_details);
        landmark = (TextView) findViewById(R.id.landmark_details);

        Spinner spinner = (Spinner) findViewById(R.id.severity_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.severity_array, R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setSelection(1);
        spinner.setOnItemSelectedListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.swachh_post, menu);
        return true;
    }

    private void postData() {
        new UploadImageTask().execute();
    }


    /**
     * {
     * "description":"Garbage Sample 2",
     * "imageUrl":"http://cdn.business2community.com/wp-content/uploads/2012/11/GarbagePile.jpg",
     * "severity":"HIGH",
     * "location":[12, 21],
     * "status":"OPEN",
     * "userId":"prasanna.venkataraman1",
     * "fbAccessToken":"TEST"
     * }
     */


    public Request post(String description, String imageUrl, String severity,
                        String location, String status, String userId, String fbAccessToken) {
        JSONObject params = null;
        try {
            params = new JSONObject();
            params.put("description", description);
            params.put("imageUrl", imageUrl);
            params.put("severity", severity);
            params.put("location", location);
            params.put("status", status);
            params.put("userId", userId);
            params.put("fbAccessToken", fbAccessToken);
        } catch (JSONException e) {
            Log.e("SwachhPostActivity", e.getMessage());
        }
        JsonObjectRequest req = new JsonObjectRequest(
                Request.Method.POST,
                AppConstants.ADD_NEW_POST,
                params,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response", "Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", "Error: " + error.getMessage());
            }
        });

        return req;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    /**
     * A simple task that uploads an image from the local Image View
     */
    public class UploadImageTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(SwachhPostActivity.this);
            progressDialog.setMessage("Posting new Swachh item...");
            progressDialog.show();
        }

        JSONObject result;

        protected String doInBackground(String... urls) {


            Bitmap bitmap = ((BitmapDrawable) swachhPostImage.getDrawable()).getBitmap();
            File f = null;

            try {
                //create a file to write bitmap data
                f = new File(Environment.getExternalStorageDirectory() + File.separator + "swachh.png");
                f.createNewFile();

                //Convert bitmap to byte array
                //Bitmap bitmap = your bitmap;
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
                byte[] bitmapdata = bos.toByteArray();

                //write the bytes in file
                FileOutputStream fos = new FileOutputStream(f);
                fos.write(bitmapdata);
                fos.flush();
                fos.close();
            } catch (Exception e) {

            }


            try {
                result = cloudinary.uploader().upload(f, Cloudinary.emptyMap());
                System.out.println("response res " + result.toString());

                cloudinaryImageId = result.getString("url");
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (JSONException e) {
                Log.e("Cloudinary Image Upload", e.getMessage());
                return null;
            }


            return cloudinaryImageId;
        }

        protected void onPostExecute(String image) {

            // making fresh volley request and getting json

            JSONObject requestObj = new JSONObject();
            try {
                requestObj.put("description", description.getText().toString());
                requestObj.put("imageUrl", cloudinaryImageId);
                requestObj.put("severity", selectedSeverity);
                requestObj.put("landmark", landmark.getText().toString());
                String latLong = sharedPreferencesController.getString("latLong");
                requestObj.put("location", latLong);
                requestObj.put("locationAddress", location);
                // No Needed as we will get username from API Key itself
                //requestObj.put("username","prasanna.venkataraman1");

            } catch (Exception e) {
                e.printStackTrace();
            }
            JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, AppConstants.SERVER_HOST_ADDRESS + AppConstants.ADD_NEW_POST,
                    //JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, AppConstants.SERVER_BASE_URL,
                    requestObj, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    try
                    {
                        progressDialog.dismiss();
                    }
                    catch (Exception e)
                    {

                    }
                    Log.d("Response", "swachh Response: " + response.toString());
                    onBackPressed();
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    Log.d("Error", "Error: " + error.getMessage());

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


            /**
             *  {
             "description":"Garbage Sample 2",
             "imageUrl":"http://cdn.business2community.com/wp-content/uploads/2012/11/GarbagePile.jpg",
             "severity":"HIGH",
             "location":[12, 21],
             "status":"OPEN",
             "userId":"prasanna.venkataraman1",
             "fbAccessToken":"TEST"
             }
             */

        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.save:
                postData();
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.swachh_photo_layout:
                selectImage(id);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            //ImageView imageView = (ImageView)findViewById(requestCode);
            if (capture.getActionCode() == CapturePhoto.PICK_IMAGE) {
                Uri targetUri = data.getData();
                if (targetUri != null)
                    capture.handleMediaPhoto(targetUri, swachhPostImage);
            } else {
                capture.handleCameraPhoto(swachhPostImage);
            }
        }
    }

    private void selectImage(final int id) {
        final CharSequence[] items = {"Click from Camera", "Choose from Gallery",
                "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(SwachhPostActivity.this);
        builder.setTitle("");
        builder.setCancelable(true);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Click from Camera")) {
                    // take photo from camera
                    capture.dispatchTakePictureIntent(CapturePhoto.SHOT_IMAGE, id);
                } else if (items[item].equals("Choose from Gallery")) {
                    // take photo from gallery
                    capture.dispatchTakePictureIntent(CapturePhoto.PICK_IMAGE, id);
                } else if (items[item].equals("Cancel")) {
                    // close the dialog
                    dialog.dismiss();
                }
            }
        });
        builder.show();
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
                location = addressList.get(0).getAddressLine(0).toString();
                sharedPreferencesController.putString("location", location );
                locationData.setText(location);
            }
        }
    }


}
