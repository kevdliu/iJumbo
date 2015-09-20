package com.ijumbo.ijumbo;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class DiningActivity extends FragmentActivity {

    private static final String URL_PREFIX = "https://pure-oasis-6199.herokuapp.com/dining-hall/";
    private static final String CARM = "carm";
    private static final String DEWICK = "dewick";
    private static final String HODGDON = "hodgdon";

    private static final String[] DROPDOWN = {"Dewick", "Carmichael", "Hodgdon"};
    public static final String[] ACROSS = {"Breakfast", "Lunch", "Dinner"};

    private PagerAdapter mPagerAdapter;
    private ViewPager mViewPager;

    private Bundle mDewick = new Bundle();
    private Bundle mCarm = new Bundle();
    private Bundle mHodgdon = new Bundle();
    Bundle[] mBundles = {mDewick, mCarm, mHodgdon};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        // getActionBar().setSelectedNavigationItem(position);
                    }
                });

        new Loader().execute();

        // Action bar setup
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setup() {
        // Dropdown spinner setup
        ActionBar.OnNavigationListener callback = new ActionBar.OnNavigationListener() {
            @Override
            public boolean onNavigationItemSelected(int position, long id) {
                // update fragments
                mPagerAdapter = new PagerAdapter(getSupportFragmentManager(),
                        new ArrayList<String>(Arrays.asList(ACROSS)),
                        mBundles[position]);
                mViewPager.setAdapter(mPagerAdapter);
                return true;
            }
        };

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Arrays.asList(DROPDOWN));
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getActionBar().setListNavigationCallbacks(dataAdapter, callback);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class Loader extends AsyncTask<String, Void, String> {

        ProgressDialog mDialog;

        @Override
        protected String doInBackground(String... params) {
            String[] urls = {URL_PREFIX + DEWICK, URL_PREFIX + CARM, URL_PREFIX + HODGDON};

            for (int i = 0; i < 3; i++) {
                // dewick, carm, hodgdon
                try {
                    InputStream input = new URL(urls[i]).openStream();
                    Gson gson = new Gson();
                    Foods f = gson.fromJson(new InputStreamReader(input, "UTF-8"), Foods.class);

                    for (Foods.Meal meal : f.mMeals) {
                        // Breakfast, Lunch, Dinner
                        String mealName = meal.mName;
                        ArrayList<String> allFoods = new ArrayList<String>();

                        // food types/sections
                        for (Foods.Menu menu : meal.mMenus) {
                            StringBuilder builder = new StringBuilder();
                            builder.append(menu.mSection.mCategory);
                            builder.append("~~~");

                            String foodNames = menu.mSection.mItems;
                            String[] foodList = foodNames.split(",");
                            for (String name : foodList) {
                                builder.append(name);
                                builder.append("~~~");
                            }

                            allFoods.add(builder.toString());
                        }

                        mBundles[i].putStringArrayList(mealName, allFoods);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            setup();
            mDialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            mDialog = new ProgressDialog(DiningActivity.this);
            mDialog.setIndeterminate(true);
            mDialog.setTitle("Loading...");
            mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    DiningActivity.this.finish();
                }
            });
            mDialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}
