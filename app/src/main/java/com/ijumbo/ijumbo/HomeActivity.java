package com.ijumbo.ijumbo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;


public class HomeActivity extends Activity {

    private static final String[] TITLES = {"News", "Locations", "Dining", "Events", "Transportation", "Links"};
    private static final int[] IMG = {R.mipmap.news, R.mipmap.locations, R.mipmap.dining, R.mipmap.events, R.mipmap.transportation, R.mipmap.links};;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        GridView gridView = (GridView) findViewById(R.id.tiles);

        GridAdapter adapter = new GridAdapter(this, TITLES, IMG, 3);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == 0) {
                    Intent news = new Intent(HomeActivity.this, NewsActivity.class);
                    HomeActivity.this.startActivity(news);
                } else if (position == 1) {
                    // Intent news = new Intent(HomeActivity.this, NewsActivity.class);
                    // HomeActivity.this.startActivity(news);
                    Toast.makeText(HomeActivity.this, "Shit ain't ready yo calm down", Toast.LENGTH_SHORT).show();
                } else if (position == 2) {
                    Intent dining = new Intent(HomeActivity.this, DiningActivity.class);
                    HomeActivity.this.startActivity(dining);
                } else if (position == 3) {
                    Intent events = new Intent(HomeActivity.this, CalendarActivity.class);
                    HomeActivity.this.startActivity(events);
                } else if (position == 4) {
                    Intent dining = new Intent(HomeActivity.this, TransportationActivity.class);
                    HomeActivity.this.startActivity(dining);
                } else if (position == 5) {
                    // Intent news = new Intent(HomeActivity.this, NewsActivity.class);
                    // HomeActivity.this.startActivity(news);
                    Toast.makeText(HomeActivity.this, "Shit ain't ready yo calm down", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
