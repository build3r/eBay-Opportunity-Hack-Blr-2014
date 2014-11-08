package in.bigo.saytrees.receiver;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.parse.ParsePushBroadcastReceiver;

import in.bigo.saytrees.activity.SplashActivity;

/**
 * Created by SPARK on 12/10/14.
 */
public class ParsePushReceiver extends ParsePushBroadcastReceiver {

    @Override
    public void onPushOpen(Context context, Intent intent) {
        Log.e("Push", "Clicked "+intent.getExtras().getString("com.parse.Data"));
        Intent i = new Intent(context, SplashActivity.class);
        i.putExtras(intent.getExtras());
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}