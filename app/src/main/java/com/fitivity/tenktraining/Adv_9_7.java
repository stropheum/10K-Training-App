package com.fitivity.tenktraining;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Adv_9_7 extends SixPartActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PART_ONE_DURATION   = 30 * 1000 * 60;
        PART_TWO_DURATION   = 10 * 1000 * 60;
        PART_THREE_DURATION = 15 * 1000 * 60;
        PART_FOUR_DURATION  = 10 * 1000 * 60;
        PART_FIVE_DURATION  = 10 * 1000 * 60;
        PART_SIX_DURATION   = 20 * 1000 * 60;

        PART_ONE_GOAL_PACE   = 5.0;
        PART_TWO_GOAL_PACE   = 15.0;
        PART_THREE_GOAL_PACE = 5.0;
        PART_FOUR_GOAL_PACE  = 15.0;
        PART_FIVE_GOAL_PACE  = 5.0;
        PART_SIX_GOAL_PACE   = 18.0;

        PART_ONE_SECONDARY_TITLE   = "5:00 min/mile";
        PART_TWO_SECONDARY_TITLE   = "15:00 min/mile";
        PART_THREE_SECONDARY_TITLE = "5:00 min/mile";
        PART_FOUR_SECONDARY_TITLE  = "15:00 min/mile";
        PART_FIVE_SECONDARY_TITLE  = "5:00 min/mile";
        PART_SIX_SECONDARY_TITLE   = "18:00 min/mile";

        super.onCreate(savedInstanceState);
        getSupportActionBar().setCustomView(R.layout.action_bar_9_7);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_adv_9_7, menu);
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
