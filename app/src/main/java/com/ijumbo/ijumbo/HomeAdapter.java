package com.ijumbo.ijumbo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HomeAdapter extends BaseAdapter {
    private Context mContext;
    private final String[] mTitles = {"News", "Locations", "Dining", "Events", "Transportation", "Links"};
    private final int[] mImg = {R.mipmap.news, R.mipmap.locations, R.mipmap.dining, R.mipmap.events, R.mipmap.transportation, R.mipmap.links};

    public HomeAdapter(Context c) {
        mContext = c;
    }

    @Override
    public int getCount() {
        return mTitles.length;
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
            grid = new View(mContext);
            grid = inflater.inflate(R.layout.tile_main, null);
        } else {
            grid = (View) convertView;
        }

        LinearLayout ll = (LinearLayout) grid.findViewById(R.id.ll);
        TextView textView = (TextView) grid.findViewById(R.id.title);
        ImageView imageView = (ImageView)grid.findViewById(R.id.img);
        textView.setText(mTitles[position]);
        imageView.setImageResource(mImg[position]);
        ll.setBackgroundColor(Color.argb(150, 150, 150, 150));

        View activityContent = ((Activity) mContext).getWindow().findViewById(Window.ID_ANDROID_CONTENT);
        int titleHeight = activityContent.getBottom() - activityContent.getTop();
        grid.setMinimumHeight(titleHeight / 3);

        return grid;
    }
}
