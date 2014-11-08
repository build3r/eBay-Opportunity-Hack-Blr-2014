package in.bigo.saytrees.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import in.bigo.saytrees.R;
import in.bigo.saytrees.model.Events;

/**
 * Created by SPARK on 08/11/14.
 */
public class EventsAdapter extends BaseAdapter {

    Activity activity;
    ArrayList<Events> events;
    private LayoutInflater inflater;

    public EventsAdapter(Activity activity, ArrayList<Events> events)
    {
        this.activity = activity;
        this.events = events;
        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Object getItem(int position) {
        return events.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if(convertView == null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.event_item_layout, null);
            holder.eventName = (TextView) convertView.findViewById(R.id.event_name);
            holder.locationName = (TextView) convertView.findViewById(R.id.event_location);
            holder.date = (TextView) convertView.findViewById(R.id.date);
            holder.like = (ImageView) convertView.findViewById(R.id.like);
            holder.comment = (ImageView) convertView.findViewById(R.id.comment);
            holder.join = (ImageView) convertView.findViewById(R.id.join);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.eventName.setText(events.get(position).getOrganizer());
        holder.locationName.setText(events.get(position).getLocation());
        Date d = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            d = sdf.parse(events.get(position).getEventTime());
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
        sdf.applyPattern("dd MM yyyy");
        String output = sdf.format(d);

        holder.date.setText(output.substring(0, 6));




        return convertView;

    }




    static class ViewHolder
    {
        TextView eventName;
        TextView locationName;
        TextView date;
        ImageView like;
        ImageView comment;
        ImageView join;
    }

}
