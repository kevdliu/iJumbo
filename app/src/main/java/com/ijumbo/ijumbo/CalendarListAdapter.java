package com.ijumbo.ijumbo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CalendarListAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Event> mEvents;

    public CalendarListAdapter(Context c, ArrayList<Event> evts) {
        mEvents = evts;
        mContext = c;
    }

    @Override
    public int getCount() {
        return mEvents.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            grid = inflater.inflate(R.layout.row_calendar, null);
        } else {
            grid = convertView;
        }

        TextView title = (TextView) grid.findViewById(R.id.title);
        TextView time = (TextView) grid.findViewById(R.id.time);
        TextView location = (TextView) grid.findViewById(R.id.location);

        Event evt = mEvents.get(position);
        title.setText(evt.mTitle);
        time.setText(evt.mTime);
        location.setText(evt.mLocation);

        return grid;
    }
}
