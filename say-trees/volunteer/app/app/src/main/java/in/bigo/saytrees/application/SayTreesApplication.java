package in.bigo.saytrees.application;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.graphics.Typeface;
import android.util.Base64;
import android.util.Log;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParsePush;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import in.bigo.saytrees.R;
import in.bigo.saytrees.utils.AppConstants;

public class SayTreesApplication extends android.app.Application {

    private static SayTreesApplication context;
    public static Typeface roboticThin, roboticLight, abelRegular;

    public SayTreesApplication() {
        context = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //initGlobalModelStore();
        setupImageDownloader();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, AppConstants.PARSE_APPID, AppConstants.PARSE_CLIENTKEY);

        // Specify an Activity to handle all pushes by default.
        //PushService.setDefaultPushCallback(this, SplashActivity.class);


        roboticThin = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");
        roboticLight = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        abelRegular = Typeface.createFromAsset(getAssets(), "fonts/Abel-Regular.ttf");

        ParsePush.subscribeInBackground("saytrees_broadcast");

        // Associate the device with a user
        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        //installation.put("user", ParseUser.getCurrentUser());
        installation.saveInBackground();
    }

    public void setupImageDownloader() {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.download) // resource or drawable
                .showImageForEmptyUri(R.drawable.download) // resource or drawable
                .showImageOnFail(R.drawable.error) // resource or drawable
                .resetViewBeforeLoading(false)  // default
                .cacheInMemory(true) // default
                .cacheOnDisk(true)
                .build();

        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .defaultDisplayImageOptions(defaultOptions)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(20 * 1024 * 1024) // 20 Mb
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                //.writeDebugLogs() // Remove for release app
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);

    }

    public void initGlobalModelStore() {
        //initiate controllers
    }

    public static Context getContext() {
        return context;
    }

    public static SayTreesApplication getApplicationObject() {
        return (SayTreesApplication) context;
    }


    public static String getAppKey() {
        // Add code to print out the key hash
        try {
            PackageInfo info = SayTreesApplication.getApplicationObject().getPackageManager().getPackageInfo(
                    "in.bigo.saytrees",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String keyHash = Base64.encodeToString(md.digest(), Base64.DEFAULT);
                Log.d("KeyHash:", keyHash);
                return keyHash;
            }
        } catch (NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
        return null;
    }


}
