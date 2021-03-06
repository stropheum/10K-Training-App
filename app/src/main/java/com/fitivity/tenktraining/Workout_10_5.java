package com.fitivity.tenktraining;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class Workout_10_5 extends OnePartActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PART_ONE_DURATION = 70 * 1000 * 60;

        PART_ONE_GOAL_PACE = 7.4;

        PART_ONE_SECONDARY_TITLE = "7:24 min/mile";

        super.onCreate(savedInstanceState);
        getSupportActionBar().setCustomView(R.layout.action_bar_10_5);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_workout_10_5, menu);
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
