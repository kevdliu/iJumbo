package com.ijumbo.ijumbo;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ijumbo.ijumbo.hlv.SectionAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class DiningListAdapter extends SectionAdapter {

    private Context mContext;
    private ArrayList<ArrayList<String>> mData = new ArrayList<ArrayList<String>>();

    public DiningListAdapter(Context c, ArrayList<String> data) {
        if (data == null) {
            mData = new ArrayList<ArrayList<String>>();
        } else {
            for (String foodStr : data) {
                mData.add(new ArrayList<String>(Arrays.asList(foodStr.split("~~~"))));
            }
        }

        mContext = c;
    }

    @Override
    public boolean hasSectionHeaderView(int section) {
        return true;
    }

    @Override
    public int numberOfSections() {
        return mData.size();
    }

    @Override
    public int numberOfRows(int section) {
        if (section < 0) {
            section = 0;
        }

        int n = mData.get(section).size() - 1;
        if (n < 0) {
            n = 0;
        }
        return n;
    }

    @Override
    public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            grid = inflater.inflate(R.layout.row_dining, null);
        } else {
            grid = convertView;
        }

        LinearLayout ll = (LinearLayout) grid.findViewById(R.id.ll);
        ll.setBackgroundColor(Color.rgb(150, 150, 150));

        TextView textView = (TextView) grid.findViewById(R.id.title);
        textView.setText(mData.get(section).get(0));

        return grid;
    }

    @Override
    public View getRowView(int section, int row, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            grid = inflater.inflate(R.layout.row_dining, null);
        } else {
            grid = convertView;
        }

        TextView textView = (TextView) grid.findViewById(R.id.title);
        textView.setText(mData.get(section).get(row + 1));

        return grid;
    }

    @Override
    public Object getRowItem(int section, int row) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
