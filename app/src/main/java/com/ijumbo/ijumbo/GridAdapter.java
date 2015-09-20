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

public class GridAdapter extends BaseAdapter {

    private Context mContext;

    private String[] mTitles;
    private int[] mImg;
    private int mRows;

    public GridAdapter(Context c, String[] titles, int[] img, int rows) {
        mTitles = titles;
        mImg = img;
        mRows = rows;
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
        grid.setMinimumHeight(titleHeight / mRows);

        return grid;
    }
}
