package com.ijumbo.ijumbo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;


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
                if (position == 2) {
                    Intent dining = new Intent(HomeActivity.this, DiningActivity.class);
                    HomeActivity.this.startActivity(dining);
                } else if (position == 4) {
                    Intent dining = new Intent(HomeActivity.this, TransportationActivity.class);
                    HomeActivity.this.startActivity(dining);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
