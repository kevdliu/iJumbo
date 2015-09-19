package com.ijumbo.ijumbo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeAdapter extends BaseAdapter {
    private Context mContext;
    private final String[] mTitles = {"News", "Locations", "Dining", "Events", "Transportation", "Links"};
    private final int[] mImg = {};

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
            grid = inflater.inflate(R.layout.row_main, null);
        } else {
            grid = (View) convertView;
        }

        TextView textView = (TextView) grid.findViewById(R.id.title);
        ImageView imageView = (ImageView)grid.findViewById(R.id.img);
        textView.setText(mTitles[position]);
        imageView.setImageResource(mImg[position]);

        return grid;
    }
}
