package in.bigo.saytrees.receiver;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.parse.ParsePushBroadcastReceiver;

import org.json.JSONObject;

import in.bigo.saytrees.activity.NotificationsActivity;

/**
 * Created by SPARK on 12/10/14.
 */
public class ParsePushReceiver extends ParsePushBroadcastReceiver {

    JSONObject obj = null;

    /**
     * {"eventId":"545ee4453e16f5bff9d76070",
     * "organizedBy":"asqs",
     * "location":"12.913552398609209,77.58476257324219",
     * "time":"2014-08-30 04:10:07",
     * "address":"L9, 27th Main Road, TMC Layout, Phase 1, J. P. Nagar, Bengaluru, Karnataka 560078, India, "
     * }
     * @param context
     * @param intent
     */

    @Override
    public void onPushOpen(Context context, Intent intent) {
        Log.d("Push", "Clicked "+intent.getExtras().getString("event"));
        try {
            obj = new JSONObject(intent.getExtras().getString("event"));
            Log.d("My App", obj.getString("event"));
           // JSONObject myObj = new JSONObject(obj.getString("alert"));


        JSONObject myObj = new JSONObject(obj.getString("event"));
        Intent i = new Intent(context, NotificationsActivity.class);
        //i.putExtras(intent.getExtras());
        i.putExtra("event_id", myObj.getString("event_id"));
        i.putExtra("event_name", myObj.getString("event_name"));
        i.putExtra("organizedBy", myObj.getString("organizedBy"));
        i.putExtra("location", myObj.getString("location"));
        i.putExtra("time", myObj.getString("time"));
        i.putExtra("address", myObj.getString("address"));
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
        } catch (Throwable t) {
            Log.e("My App", "Could not parse malformed JSON: " + intent.getExtras().getString("com.parse.Data")) ;
        }
    }
}