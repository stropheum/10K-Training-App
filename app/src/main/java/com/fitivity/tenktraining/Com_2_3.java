package com.fitivity.tenktraining;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Com_2_3 extends FourteenPartActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PART_ONE_DURATION      = 3 * 1000 * 60;
        PART_TWO_DURATION      = 3 * 1000 * 60;
        PART_THREE_DURATION    = 3 * 1000 * 60;
        PART_FOUR_DURATION     = 3 * 1000 * 60;
        PART_FIVE_DURATION     = 3 * 1000 * 60;
        PART_SIX_DURATION      = 3 * 1000 * 60;
        PART_SEVEN_DURATION    = 3 * 1000 * 60;
        PART_EIGHT_DURATION    = 3 * 1000 * 60;
        PART_NINE_DURATION     = 3 * 1000 * 60;
        PART_TEN_DURATION      = 3 * 1000 * 60;
        PART_ELEVEN_DURATION   = 3 * 1000 * 60;
        PART_TWELVE_DURATION   = 3 * 1000 * 60;
        PART_THIRTEEN_DURATION = 20 * 1000 * 60;
        PART_FOURTEEN_DURATION = 15 * 1000 * 60;

        PART_ONE_GOAL_PACE      = 6.0;
        PART_TWO_GOAL_PACE      = 15.0;
        PART_THREE_GOAL_PACE    = 6.0;
        PART_FOUR_GOAL_PACE     = 15.0;
        PART_FIVE_GOAL_PACE     = 6.0;
        PART_SIX_GOAL_PACE      = 15.0;
        PART_SEVEN_GOAL_PACE    = 6.0;
        PART_EIGHT_GOAL_PACE    = 15.0;
        PART_NINE_GOAL_PACE     = 6.0;
        PART_TEN_GOAL_PACE      = 15.0;
        PART_ELEVEN_GOAL_PACE   = 6.0;
        PART_TWELVE_GOAL_PACE   = 15.0;
        PART_THIRTEEN_GOAL_PACE = 6.0;
        PART_FOURTEEN_GOAL_PACE = 18.0;

        PART_ONE_SECONDARY_TITLE      = "6:00 min/mile";
        PART_TWO_SECONDARY_TITLE      = "15:00 min/mile";
        PART_THREE_SECONDARY_TITLE    = "6:00 min/mile";
        PART_FOUR_SECONDARY_TITLE     = "15:00 min/mile";
        PART_FIVE_SECONDARY_TITLE     = "6:00 min/mile";
        PART_SIX_SECONDARY_TITLE      = "15:00 min/mile";
        PART_SEVEN_SECONDARY_TITLE    = "6:00 min/mile";
        PART_EIGHT_SECONDARY_TITLE    = "15:00 min/mile";
        PART_NINE_SECONDARY_TITLE     = "6:00 min/mile";
        PART_TEN_SECONDARY_TITLE      = "15:00 min/mile";
        PART_ELEVEN_SECONDARY_TITLE   = "6:00 min/mile";
        PART_TWELVE_SECONDARY_TITLE   = "15:00 min/mile";
        PART_THIRTEEN_SECONDARY_TITLE = "6:00 min/mile";
        PART_FOURTEEN_SECONDARY_TITLE = "18:00 min/mile";
        
        super.onCreate(savedInstanceState);
        getSupportActionBar().setCustomView(R.layout.action_bar_2_3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_com_2_3, menu);
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
