package com.fitivity.tenktraining;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Beg_5_4 extends OnePartActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PART_ONE_DURATION = 40 * 1000 * 60;

        PART_ONE_GOAL_PACE = 17.0;

        PART_ONE_SECONDARY_TITLE = "17:00 min/mile";

        super.onCreate(savedInstanceState);
        getSupportActionBar().setCustomView(R.layout.action_bar_5_4);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_beg_5_4, menu);
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
