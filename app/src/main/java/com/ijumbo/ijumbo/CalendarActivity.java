package com.ijumbo.ijumbo;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.ListView;

public class CalendarActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        final ListView list = (ListView) findViewById(R.id.list);

        CalendarView calendar = (CalendarView) findViewById(R.id.calendar);
        calendar.setFirstDayOfWeek(2);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                // DiningListAdapter adapter = new DiningListAdapter(getActivity(), args.getStringArrayList(TITLES));
                // list.setAdapter(adapter);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
