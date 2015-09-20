package com.ijumbo.ijumbo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class PagerAdapter extends FragmentStatePagerAdapter {

    public static String WEB_FRAGMENT = "web";

    private ArrayList<Bundle> mBundles = new ArrayList<Bundle>();
    private int mCount;

    public PagerAdapter(FragmentManager fm, ArrayList<Bundle> bundles, int count) {
        super(fm);
        mBundles = bundles;
        mCount = count;
    }

    @Override
    public Fragment getItem(int i) {
        Bundle b = mBundles.get(i);

        Fragment fragment;
        if (b.getBoolean(WEB_FRAGMENT)) {
            fragment = new WebFragment();
        } else {
            fragment = new ListFragment();
        }

        fragment.setArguments(mBundles.get(i));
        return fragment;
    }

    @Override
    public int getCount() {
        return mCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + (position + 1);
    }
}
