package com.ijumbo.ijumbo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class PagerAdapter extends FragmentStatePagerAdapter {

    private Bundle mBundle;
    private ArrayList<String> mTitles;

    public PagerAdapter(FragmentManager fm, ArrayList<String> titles, Bundle bundle) {
        super(fm);
        mBundle = bundle;
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new ListFragment();;

        Bundle b = new Bundle();
        b.putStringArrayList(ListFragment.DATA, mBundle.getStringArrayList(DiningActivity.ACROSS[i]));
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public int getCount() {
        return mTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
