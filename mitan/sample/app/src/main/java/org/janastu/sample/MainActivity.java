package org.janastu.sample;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.janastu.sample.compasslibrary.CompassSensorsActivity;
import org.janastu.sample.compasslibrary.CompassView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class MainActivity extends CompassSensorsActivity implements LocationListener {
    private static int RESULT_LOAD_IMAGE = 1;
    Double lat,lon;
    double currentLat;
    TextView distanceText;
    double currentLon;
    LocationManager locationManager;
    ProgressDialog progressDialog;
    Criteria criteria;
    CompassView compassView;
    private static final long MIN_TIME_FOR_UPDATE = 1000 * 60 * 2;
    private static final long MIN_DISTANCE_FOR_UPDATE = 10;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Getting Location...");
        compassView = (CompassView) findViewById(R.id.compassView);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Button buttonLoadImage = (Button) findViewById(R.id.buttonLoadPicture);
        distanceText = (TextView) findViewById(R.id.distance);
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
        {
            criteria = new Criteria();
            String provider = locationManager.getBestProvider(criteria, true);
            Location location;
            location = locationManager.getLastKnownLocation(provider);
            progressDialog.show();
            if (location == null){
                locationManager.requestLocationUpdates(provider,MIN_TIME_FOR_UPDATE, MIN_DISTANCE_FOR_UPDATE, MainActivity.this);
            }
            else {
                setLocation(location);
            }

        }
        else
        {
            showalert();
        }
        ImageView imageView = (ImageView) findViewById(R.id.imgView);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

//        ExifInterface exifSource = null;
//            try {
//                exifSource = new ExifInterface((String)imageView.getTag());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//                Log.v("LATITUDE",getExifTag(exifSource,ExifInterface.TAG_GPS_LATITUDE));
//                Log.v("LONGITUDE",getExifTag(exifSource,ExifInterface.TAG_GPS_LONGITUDE));


    }

    private void setLocation(Location location) {

        progressDialog.dismiss();

          currentLat = location.getLatitude();
          currentLon = location.getLongitude();


    }

    private void setDistance() {
        Location loc1 = new Location("");
            loc1.setLatitude(currentLat);
            loc1.setLongitude(currentLon);

            Location loc2 = new Location("");
            loc2.setLatitude(lat);
            loc2.setLongitude(lon);

            float distanceInMeters = loc1.distanceTo(loc2);
            distanceText.setText("Distance : " + distanceInMeters/1000 + "Km");
        compassView.initializeCompass(loc1, loc2, R.drawable.arrow);
            Log.v("Distance",""+distanceInMeters/1000);

    }

    private void showalert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setMessage("Location Services are disabled in your device. Would you like to enable it?").setCancelable(false).setPositiveButton("Goto Settings Page",new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int id){
                Intent callGPSSettingIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(callGPSSettingIntent);
            }
        });
        alertDialogBuilder.setNegativeButton("Cancel",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                finish();
            }
        });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            ImageView imageView = (ImageView) findViewById(R.id.imgView);
            imageView.setImageBitmap(decodeFile(new File(picturePath), 320, 480));
            try {
                ExifInterface exif = new ExifInterface(picturePath);
                new geoDegree(exif);
                setDistance();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Bitmap decodeFile(File f,int WIDTH,int HIGHT){
        try {
            //Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f),null,o);

            //The new size we want to scale to
            final int REQUIRED_WIDTH=WIDTH;
            final int REQUIRED_HIGHT=HIGHT;
            //Find the correct scale value. It should be the power of 2.
            int scale=1;
            while(o.outWidth/scale/2>=REQUIRED_WIDTH && o.outHeight/scale/2>=REQUIRED_HIGHT)
                scale*=2;

            //Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize=scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {}
        return null;
    }

    private String getExifTag(ExifInterface exif,String tag){
        String attribute = exif.getAttribute(tag);

        return (null != attribute ? attribute : "");
    }

    @Override
    public void onLocationChanged(Location location) {

        progressDialog.show();
        setLocation(location);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    public class geoDegree {
        private boolean valid = false;
        Double Latitude, Longitude;
        geoDegree(ExifInterface exif) {
            String attrLATITUDE = exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE);
            String attrLATITUDE_REF = exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF);
            String attrLONGITUDE = exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE);
            String attrLONGITUDE_REF = exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF);

            if((attrLATITUDE !=null)
                    && (attrLATITUDE_REF !=null)
                    && (attrLONGITUDE != null)
                    && (attrLONGITUDE_REF !=null))
            {
                valid = true;

                if(attrLATITUDE_REF.equals("N")){
                    lat = convertToDegree(attrLATITUDE);
                    Log.v("Lat",""+lat);
                }
                else{
                    lat = 0 - convertToDegree(attrLATITUDE);
                    Log.v("Lat",""+lat);
                }

                if(attrLONGITUDE_REF.equals("E")){
                    lon = convertToDegree(attrLONGITUDE);
                    Log.v("Lon",""+lon);
                }
                else{
                    lon = 0 - convertToDegree(attrLONGITUDE);
                    Log.v("Lon",""+lon);
                }

            }
        };

        private Double convertToDegree(String stringDMS){
            Double result = null;
            String[] DMS = stringDMS.split(",", 3);

            String[] stringD = DMS[0].split("/", 2);
            Double D0 = new Double(stringD[0]);
            Double D1 = new Double(stringD[1]);
            Double FloatD = D0/D1;

            String[] stringM = DMS[1].split("/", 2);
            Double M0 = new Double(stringM[0]);
            Double M1 = new Double(stringM[1]);
            Double FloatM = M0/M1;

            String[] stringS = DMS[2].split("/", 2);
            Double S0 = new Double(stringS[0]);
            Double S1 = new Double(stringS[1]);
            Double FloatS = S0/S1;

            result = new Double(FloatD + (FloatM/60) + (FloatS/3600));

            return result;


        };

        public boolean isValid()
        {
            return valid;
        }

        @Override
        public String toString() {
            // TODO Auto-generated method stub
            return (String.valueOf(Latitude)
                    + ", "
                    + String.valueOf(Longitude));
        }

        public int getLatitudeE6(){
            return (int)(Latitude*1000000);
        }

        public int getLongitudeE6(){
            return (int)(Longitude*1000000);
        }

    }
}
