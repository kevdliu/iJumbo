package com.ijumbo.ijumbo;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

public class DiningActivity extends FragmentActivity {

    private static final String[] TITLES = {"Dewick", "Carmichael", "Hodgeton"};

    private PagerAdapter mPagerAdapter;
    private ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        // view pager setup
        // pass data to pager and fragments
        ArrayList<Bundle> bundles = new ArrayList<Bundle>();

        for (int i = 0; i < 3; i++) {
            Bundle b = new Bundle();
            b.putString("ARG", TITLES[i]);
            bundles.add(b);
        }

        mPagerAdapter = new PagerAdapter(getSupportFragmentManager(), bundles, TITLES.length);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        getActionBar().setSelectedNavigationItem(position);
                    }
                });

        // Action bar setup
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
            }
        };

        for (int i = 0; i < TITLES.length; i++) {
            actionBar.addTab(actionBar.newTab()
                    .setText(TITLES[i])
                    .setTabListener(tabListener));
        }
    }
}
