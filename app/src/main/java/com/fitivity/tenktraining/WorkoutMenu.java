package com.fitivity.tenktraining;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;


public class WorkoutMenu extends ActionBarActivity {

    LinearLayout[][] layout;
    CheckBox[] checkBoxArray;
    boolean[] difficulty;

    final int BEGINNER = 0;
    final int INTERMEDIATE = 1;
    final int COMPETITIVE = 2;
    final int ADVANCED = 3;
    final int SET = 4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_menu);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);

        // Disable screen timeout while workout is active
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        layout = new LinearLayout[12][7];
        checkBoxArray = new CheckBox[84];
        getCheckBoxStates();

        difficulty = new boolean[5];
        for (int i = 0; i < difficulty.length - 1; i++) {
            difficulty[i] = false;
        }

        difficulty[SET] = load("difficulty " + SET);

        configureSettingsButton();

        if (!difficulty[SET]) {
            findViewById(R.id.menu_main_layout).post(new Runnable() {
                public void run() {
                    ImageView difficultyButton = (ImageView) findViewById(R.id.difficultyButton);
                    difficultyButton.performClick();
                }
            });

        }

        // Initialize click listeners for workout buttons
        configureWorkoutButtons();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_workout_menu, menu);
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

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_settings).setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onPause() {
        super.onPause();
        for (int i = 0; i < checkBoxArray.length-2; i++) {
            save(i + "", checkBoxArray[i].isChecked());
        }
        for (int i = 0; i < difficulty.length; i++) {
            save("difficulty " + i, difficulty[i]);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        for (int i = 0; i < checkBoxArray.length-2; i++) {
            checkBoxArray[i].setChecked(load(i + ""));
        }

        for (int i = 0; i < difficulty.length; i++) {
            difficulty[i] = load("difficulty " + i);
        }
    }

    private void save(final String key, final boolean isChecked) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, isChecked);
        editor.commit();
    }

    private boolean load(final String key) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }

    /**
     * Retrieve the states of all check boxes and stores them in an array
     */
    private void getCheckBoxStates() {
        checkBoxArray[0]  = (CheckBox) findViewById(R.id.box_1_1);
        checkBoxArray[1]  = (CheckBox) findViewById(R.id.box_1_2);
        checkBoxArray[2]  = (CheckBox) findViewById(R.id.box_1_3);
        checkBoxArray[3]  = (CheckBox) findViewById(R.id.box_1_4);
        checkBoxArray[4]  = (CheckBox) findViewById(R.id.box_1_5);
        checkBoxArray[5]  = (CheckBox) findViewById(R.id.box_1_6);
        checkBoxArray[6]  = (CheckBox) findViewById(R.id.box_1_7);
        checkBoxArray[7]  = (CheckBox) findViewById(R.id.box_2_1);
        checkBoxArray[8]  = (CheckBox) findViewById(R.id.box_2_2);
        checkBoxArray[9]  = (CheckBox) findViewById(R.id.box_2_3);
        checkBoxArray[10] = (CheckBox) findViewById(R.id.box_2_4);
        checkBoxArray[11] = (CheckBox) findViewById(R.id.box_2_5);
        checkBoxArray[12] = (CheckBox) findViewById(R.id.box_2_6);
        checkBoxArray[13] = (CheckBox) findViewById(R.id.box_2_7);
        checkBoxArray[14] = (CheckBox) findViewById(R.id.box_3_1);
        checkBoxArray[15] = (CheckBox) findViewById(R.id.box_3_2);
        checkBoxArray[16] = (CheckBox) findViewById(R.id.box_3_3);
        checkBoxArray[17] = (CheckBox) findViewById(R.id.box_3_4);
        checkBoxArray[18] = (CheckBox) findViewById(R.id.box_3_5);
        checkBoxArray[19] = (CheckBox) findViewById(R.id.box_3_6);
        checkBoxArray[20] = (CheckBox) findViewById(R.id.box_3_7);
        checkBoxArray[21] = (CheckBox) findViewById(R.id.box_4_1);
        checkBoxArray[22] = (CheckBox) findViewById(R.id.box_4_2);
        checkBoxArray[23] = (CheckBox) findViewById(R.id.box_4_3);
        checkBoxArray[24] = (CheckBox) findViewById(R.id.box_4_4);
        checkBoxArray[25] = (CheckBox) findViewById(R.id.box_4_5);
        checkBoxArray[26] = (CheckBox) findViewById(R.id.box_4_6);
        checkBoxArray[27] = (CheckBox) findViewById(R.id.box_4_7);
        checkBoxArray[28] = (CheckBox) findViewById(R.id.box_5_1);
        checkBoxArray[29] = (CheckBox) findViewById(R.id.box_5_2);
        checkBoxArray[30] = (CheckBox) findViewById(R.id.box_5_3);
        checkBoxArray[31] = (CheckBox) findViewById(R.id.box_5_4);
        checkBoxArray[32] = (CheckBox) findViewById(R.id.box_5_5);
        checkBoxArray[33] = (CheckBox) findViewById(R.id.box_5_6);
        checkBoxArray[34] = (CheckBox) findViewById(R.id.box_5_7);
        checkBoxArray[35] = (CheckBox) findViewById(R.id.box_6_1);
        checkBoxArray[36] = (CheckBox) findViewById(R.id.box_6_2);
        checkBoxArray[37] = (CheckBox) findViewById(R.id.box_6_3);
        checkBoxArray[38] = (CheckBox) findViewById(R.id.box_6_4);
        checkBoxArray[39] = (CheckBox) findViewById(R.id.box_6_5);
        checkBoxArray[40] = (CheckBox) findViewById(R.id.box_6_6);
        checkBoxArray[41] = (CheckBox) findViewById(R.id.box_6_7);
        checkBoxArray[42] = (CheckBox) findViewById(R.id.box_7_1);
        checkBoxArray[43] = (CheckBox) findViewById(R.id.box_7_2);
        checkBoxArray[44] = (CheckBox) findViewById(R.id.box_7_3);
        checkBoxArray[45] = (CheckBox) findViewById(R.id.box_7_4);
        checkBoxArray[46] = (CheckBox) findViewById(R.id.box_7_5);
        checkBoxArray[47] = (CheckBox) findViewById(R.id.box_7_6);
        checkBoxArray[48] = (CheckBox) findViewById(R.id.box_7_7);
        checkBoxArray[49] = (CheckBox) findViewById(R.id.box_8_1);
        checkBoxArray[50] = (CheckBox) findViewById(R.id.box_8_2);
        checkBoxArray[51] = (CheckBox) findViewById(R.id.box_8_3);
        checkBoxArray[52] = (CheckBox) findViewById(R.id.box_8_4);
        checkBoxArray[53] = (CheckBox) findViewById(R.id.box_8_5);
        checkBoxArray[54] = (CheckBox) findViewById(R.id.box_8_6);
        checkBoxArray[55] = (CheckBox) findViewById(R.id.box_8_7);
        checkBoxArray[56] = (CheckBox) findViewById(R.id.box_9_1);
        checkBoxArray[57] = (CheckBox) findViewById(R.id.box_9_2);
        checkBoxArray[58] = (CheckBox) findViewById(R.id.box_9_3);
        checkBoxArray[59] = (CheckBox) findViewById(R.id.box_9_4);
        checkBoxArray[60] = (CheckBox) findViewById(R.id.box_9_5);
        checkBoxArray[61] = (CheckBox) findViewById(R.id.box_9_6);
        checkBoxArray[62] = (CheckBox) findViewById(R.id.box_9_7);
        checkBoxArray[63] = (CheckBox) findViewById(R.id.box_10_6);
        checkBoxArray[64] = (CheckBox) findViewById(R.id.box_10_6);
        checkBoxArray[65] = (CheckBox) findViewById(R.id.box_10_6);
        checkBoxArray[66] = (CheckBox) findViewById(R.id.box_10_6);
        checkBoxArray[67] = (CheckBox) findViewById(R.id.box_10_6);
        checkBoxArray[68] = (CheckBox) findViewById(R.id.box_10_6);
        checkBoxArray[69] = (CheckBox) findViewById(R.id.box_10_6);
        checkBoxArray[70] = (CheckBox) findViewById(R.id.box_11_6);
        checkBoxArray[71] = (CheckBox) findViewById(R.id.box_11_6);
        checkBoxArray[72] = (CheckBox) findViewById(R.id.box_11_6);
        checkBoxArray[73] = (CheckBox) findViewById(R.id.box_11_6);
        checkBoxArray[74] = (CheckBox) findViewById(R.id.box_11_6);
        checkBoxArray[75] = (CheckBox) findViewById(R.id.box_11_6);
        checkBoxArray[76] = (CheckBox) findViewById(R.id.box_11_6);
        checkBoxArray[77] = (CheckBox) findViewById(R.id.box_12_6);
        checkBoxArray[78] = (CheckBox) findViewById(R.id.box_12_6);
        checkBoxArray[79] = (CheckBox) findViewById(R.id.box_12_6);
        checkBoxArray[80] = (CheckBox) findViewById(R.id.box_12_6);
        checkBoxArray[81] = (CheckBox) findViewById(R.id.box_12_6);
        checkBoxArray[82] = (CheckBox) findViewById(R.id.box_12_6);
        checkBoxArray[83] = (CheckBox) findViewById(R.id.box_12_6);
    }

    /**
     * Configure action listener for settings button to open difficulty dialogue
     */
    private void configureSettingsButton() {
        ImageView difficultyButton = (ImageView) findViewById(R.id.difficultyButton);
        difficultyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater)getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);

                final View popupView = inflater.inflate(R.layout.difficulty_selection_menu, null);
                final PopupWindow popup = new PopupWindow(popupView,
                        android.app.ActionBar.LayoutParams.WRAP_CONTENT,
                        android.app.ActionBar.LayoutParams.WRAP_CONTENT);
                popup.setBackgroundDrawable(null);
                popup.showAtLocation(findViewById(R.id.logo), Gravity.CENTER, 0, 0);

                // Load saved radio button states
                RadioButton[] radioGroup = new RadioButton[5];
                radioGroup[0] = (RadioButton) popupView.findViewById(R.id.beginner_button);
                radioGroup[1] = (RadioButton) popupView.findViewById(R.id.intermediate_button);
                radioGroup[2] = (RadioButton) popupView.findViewById(R.id.competitive_button);
                radioGroup[3] = (RadioButton) popupView.findViewById(R.id.advanced_button);
                radioGroup[4] = (RadioButton) popupView.findViewById(R.id.elite_button);

                for (int i = 0; i < radioGroup.length; i++) {
                    if (difficulty[i]) {
                        radioGroup[i].setChecked(true);
                    }
                }

                // Dim background on click
                WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
                WindowManager.LayoutParams p = (WindowManager.LayoutParams) popupView.getLayoutParams();
                p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                p.dimAmount = 0.7f;
                wm.updateViewLayout(popupView, p);

                // Configure text to trigger radio clicks
                LinearLayout bRow = (LinearLayout) popupView.findViewById(R.id.beginner_row);
                bRow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RadioButton button = (RadioButton) popupView.findViewById(R.id.beginner_button);
                        button.setChecked(true);
                        for (int i = 0; i < difficulty.length; i++) {
                            difficulty[i] = false;
                        }
                        difficulty[BEGINNER] = true;
                        difficulty[SET] = true;
                        configureWorkoutButtons();
                    }
                });

                LinearLayout iRow = (LinearLayout) popupView.findViewById(R.id.intermediate_row);
                iRow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RadioButton button = (RadioButton) popupView.findViewById(R.id.intermediate_button);
                        button.setChecked(true);
                        for (int i = 0; i < difficulty.length; i++) {
                            difficulty[i] = false;
                        }
                        difficulty[INTERMEDIATE] = true;
                        difficulty[SET] = true;
                        configureWorkoutButtons();
                    }
                });

                LinearLayout cRow = (LinearLayout) popupView.findViewById(R.id.competitive_row);
                cRow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RadioButton button = (RadioButton) popupView.findViewById(R.id.competitive_button);
                        button.setChecked(true);
                        for (int i = 0; i < difficulty.length; i++) {
                            difficulty[i] = false;
                        }
                        difficulty[COMPETITIVE] = true;
                        difficulty[SET] = true;
                        configureWorkoutButtons();
                    }
                });

                LinearLayout aRow = (LinearLayout) popupView.findViewById(R.id.advanced_row);
                aRow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RadioButton button = (RadioButton) popupView.findViewById(R.id.advanced_button);
                        button.setChecked(true);
                        for (int i = 0; i < difficulty.length; i++) {
                            difficulty[i] = false;
                        }
                        difficulty[ADVANCED] = true;
                        difficulty[SET] = true;
                        configureWorkoutButtons();
                    }
                });

                // Configure Choose button
                TextView chooseButton = (TextView) popupView.findViewById(R.id.chooseButton);
                chooseButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popup.dismiss();
                    }
                });
            }
        });
    }

    public void configureWorkoutButtons() {
        configureButton_1_1();
        configureButton_1_2();
        configureButton_1_3();
        configureButton_1_4();
        configureButton_1_5();
        configureButton_1_6();
        configureButton_1_7();

        configureButton_2_1();
        configureButton_2_2();
        configureButton_2_3();
        configureButton_2_4();
        configureButton_2_5();
        configureButton_2_6();
        configureButton_2_7();

        configureButton_3_1();
        configureButton_3_2();
        configureButton_3_3();
        configureButton_3_4();
        configureButton_3_5();
        configureButton_3_6();
        configureButton_3_7();

        configureButton_4_1();
        configureButton_4_2();
        configureButton_4_3();
        configureButton_4_4();
        configureButton_4_5();
        configureButton_4_6();
        configureButton_4_7();

        configureButton_5_1();
        configureButton_5_2();
        configureButton_5_3();
        configureButton_5_4();
        configureButton_5_5();
        configureButton_5_6();
        configureButton_5_7();

        configureButton_6_1();
        configureButton_6_2();
        configureButton_6_3();
        configureButton_6_4();
        configureButton_6_5();
        configureButton_6_6();
        configureButton_6_7();

        configureButton_7_1();
        configureButton_7_2();
        configureButton_7_3();
        configureButton_7_4();
        configureButton_7_5();
        configureButton_7_6();
        configureButton_7_7();

        configureButton_8_1();
        configureButton_8_2();
        configureButton_8_3();
        configureButton_8_4();
        configureButton_8_5();
        configureButton_8_6();
        configureButton_8_7();

        configureButton_9_1();
        configureButton_9_2();
        configureButton_9_3();
        configureButton_9_4();
        configureButton_9_5();
        configureButton_9_6();
        configureButton_9_7();

        configureButton_10_1();
        configureButton_10_2();
        configureButton_10_3();
        configureButton_10_4();
        configureButton_10_5();
        configureButton_10_6();
        configureButton_10_7();

        configureButton_11_1();
        configureButton_11_2();
        configureButton_11_3();
        configureButton_11_4();
        configureButton_11_5();
        configureButton_11_6();
        configureButton_11_7();

        configureButton_12_1();
        configureButton_12_2();
        configureButton_12_3();
        configureButton_12_4();
        configureButton_12_5();
        configureButton_12_6();
        configureButton_12_7();
    }

    /**
     * Configures the click listener for workout button one
     */
    private void configureButton_1_1() {
        layout[0][0] = (LinearLayout) findViewById(R.id.row_1_1);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_1_1_image);

        layout[0][0].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l1);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        }

        layout[0][0].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[0][0].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[0][0].setBackgroundColor(Color.WHITE);
                        
                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_1_1.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_1_1.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_1_1.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_1_1.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[0][0].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button two
     */
    private void configureButton_1_2() {
        layout[0][1] = (LinearLayout) findViewById(R.id.row_1_2);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_1_2_image);

        layout[0][1].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l1);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        }

        layout[0][1].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[0][1].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[0][1].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_1_2.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_1_2.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_1_2.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_1_2.class));
                        }
                        
                        startActivity(new Intent(getApplicationContext(), Workout_1_2.class));
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[0][1].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button three
     */
    private void configureButton_1_3() {
        layout[0][2] = (LinearLayout) findViewById(R.id.row_1_3);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_1_3_image);

        layout[0][2].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l1);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        }

        layout[0][2].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[0][2].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[0][2].setBackgroundColor(Color.WHITE);
                        
                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_1_3.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_1_3.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_1_3.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_1_3.class));
                        }

                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[0][2].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button four
     */
    private void configureButton_1_4() {
        layout[0][3] = (LinearLayout) findViewById(R.id.row_1_4);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_1_4_image);

        layout[0][3].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l1);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        }

        layout[0][3].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[0][3].setBackgroundColor(0xFFe1e1e1);
                        break;
                    case MotionEvent.ACTION_UP:
                        layout[0][3].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_1_4.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_1_4.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_1_4.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_1_4.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[0][3].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button five
     */
    private void configureButton_1_5() {
        layout[0][4] = (LinearLayout) findViewById(R.id.row_1_5);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_1_5_image);

        layout[0][4].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l1);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        }

        layout[0][4].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[0][4].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[0][4].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_1_5.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_1_5.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_1_5.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_1_5.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[0][4].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 1_6
     */
    private void configureButton_1_6() {
        layout[0][5] = (LinearLayout) findViewById(R.id.row_1_6);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_1_6_image);

        layout[0][5].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l1);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        }

        layout[0][5].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[0][5].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[0][5].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_1_6.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_1_6.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[0][5].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 1_7
     */
    private void configureButton_1_7() {
        layout[0][6] = (LinearLayout) findViewById(R.id.row_1_7);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_1_7_image);

        layout[0][6].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l1);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        }

        layout[0][6].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[0][6].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[0][6].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_1_7.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_1_7.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_1_7.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_1_7.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[0][6].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 2_1
     */
    private void configureButton_2_1() {
        layout[1][0] = (LinearLayout) findViewById(R.id.row_2_1);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_2_1_image);

        layout[1][0].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        }

        layout[1][0].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[1][0].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[1][0].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[1][0].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 2_2
     */
    private void configureButton_2_2() {
        layout[1][1] = (LinearLayout) findViewById(R.id.row_2_2);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_2_2_image);

        layout[1][1].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l1);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        }

        layout[1][1].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[1][1].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[1][1].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_2_2.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_2_2.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_2_2.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_2_2.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[1][1].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 2_3
     */
    private void configureButton_2_3() {
        layout[1][2] = (LinearLayout) findViewById(R.id.row_2_3);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_2_3_image);

        layout[1][2].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l1);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        }

        layout[1][2].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[1][2].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[1][2].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_2_3.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_2_3.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_2_3.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_2_3.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[1][2].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 2_4
     */
    private void configureButton_2_4() {
        layout[1][3] = (LinearLayout) findViewById(R.id.row_2_4);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_2_4_image);

        layout[1][3].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l2);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        }

        layout[1][3].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[1][3].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[1][3].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_2_4.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_2_4.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_2_4.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_2_4.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[1][3].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 2_5
     */
    private void configureButton_2_5() {
        layout[1][4] = (LinearLayout) findViewById(R.id.row_2_5);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_2_5_image);

        layout[1][4].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l2);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        }

        layout[1][4].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[1][4].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[1][4].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_2_5.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_2_5.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_2_5.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_2_5.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[1][4].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 2_6
     */
    private void configureButton_2_6() {
        layout[1][5] = (LinearLayout) findViewById(R.id.row_2_6);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_2_6_image);

        layout[1][5].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l2);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l2);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        }

        layout[1][5].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[1][5].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[1][5].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_2_6.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_2_6.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[1][5].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 2_7
     */
    private void configureButton_2_7() {
        layout[1][6] = (LinearLayout) findViewById(R.id.row_2_7);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_2_7_image);

        layout[1][6].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l1);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        }

        layout[1][6].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[1][6].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[1][6].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_2_7.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_2_7.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_2_7.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_2_7.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[1][6].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 3_1
     */
    private void configureButton_3_1() {
        layout[2][0] = (LinearLayout) findViewById(R.id.row_3_1);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_3_1_image);

        layout[2][0].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l2);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        }

        layout[2][0].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[2][0].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[2][0].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_3_1.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[2][0].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 3_2
     */
    private void configureButton_3_2() {
        layout[2][1] = (LinearLayout) findViewById(R.id.row_3_2);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_3_2_image);

        layout[2][1].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l2);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        }

        layout[2][1].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[2][1].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[2][1].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_3_2.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_3_2.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_3_2.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_3_2.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[2][1].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 3_3
     */
    private void configureButton_3_3() {
        layout[2][2] = (LinearLayout) findViewById(R.id.row_3_3);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_3_3_image);

        layout[2][2].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l1);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        }

        layout[2][2].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[2][2].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[2][2].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_3_3.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_3_3.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_3_3.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_3_3.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[2][2].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 3_4
     */
    private void configureButton_3_4() {
        layout[2][3] = (LinearLayout) findViewById(R.id.row_3_4);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_3_4_image);

        layout[2][3].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l2);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l2);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        }

        layout[2][3].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[2][3].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[2][3].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_3_4.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_3_4.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_3_4.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_3_4.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[2][3].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 3_5
     */
    private void configureButton_3_5() {
        layout[2][4] = (LinearLayout) findViewById(R.id.row_3_5);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_3_5_image);

        layout[2][4].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        }

        layout[2][4].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[2][4].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[2][4].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_3_5.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_3_5.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_3_5.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_3_5.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[2][4].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 3_6
     */
    private void configureButton_3_6() {
        layout[2][5] = (LinearLayout) findViewById(R.id.row_3_6);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_3_6_image);

        layout[2][5].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        }

        layout[2][5].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[2][5].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[2][5].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_3_6.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[2][5].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 3_7
     */
    private void configureButton_3_7() {
        layout[2][6] = (LinearLayout) findViewById(R.id.row_3_7);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_3_7_image);

        layout[2][6].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l2);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        }

        layout[2][6].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[2][6].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[2][6].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_3_7.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_3_7.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_3_7.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_3_7.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[2][6].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 4_1
     */
    private void configureButton_4_1() {
        layout[3][0] = (LinearLayout) findViewById(R.id.row_4_1);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_4_1_image);

        layout[3][0].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        }

        layout[3][0].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[3][0].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[3][0].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[3][0].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 4_2
     */
    private void configureButton_4_2() {
        layout[3][1] = (LinearLayout) findViewById(R.id.row_4_2);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_4_2_image);

        layout[3][1].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        }

        layout[3][1].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[3][1].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[3][1].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_4_2.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_4_2.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_4_2.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_4_2.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[3][1].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 4_3
     */
    private void configureButton_4_3() {
        layout[3][2] = (LinearLayout) findViewById(R.id.row_4_3);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_4_3_image);

        layout[3][2].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        }

        layout[3][2].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[3][2].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[3][2].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_4_3.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_4_3.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_4_3.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_4_3.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[3][2].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 4_4
     */
    private void configureButton_4_4() {
        layout[3][3] = (LinearLayout) findViewById(R.id.row_4_4);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_4_4_image);

        layout[3][3].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l1);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        }

        layout[3][3].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[3][3].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[3][3].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_4_4.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_4_4.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_4_4.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[3][3].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 4_5
     */
    private void configureButton_4_5() {
        layout[3][4] = (LinearLayout) findViewById(R.id.row_4_5);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_4_5_image);

        layout[3][4].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        }

        layout[3][4].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[3][4].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[3][4].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_4_5.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_4_5.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_4_5.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_4_5.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[3][4].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 4_6
     */
    private void configureButton_4_6() {
        layout[3][5] = (LinearLayout) findViewById(R.id.row_4_6);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_4_6_image);

        layout[3][5].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        }

        layout[3][5].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[3][5].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[3][5].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_4_6.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[3][5].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 4_7
     */
    private void configureButton_4_7() {
        layout[3][6] = (LinearLayout) findViewById(R.id.row_4_7);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_4_7_image);

        layout[3][6].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        }

        layout[3][6].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[3][6].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[3][6].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_4_7.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_4_7.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_4_7.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_4_7.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[3][6].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 5_1
     */
    private void configureButton_5_1() {
        layout[4][0] = (LinearLayout) findViewById(R.id.row_5_1);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_5_1_image);

        layout[4][0].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        }

        layout[4][0].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[4][0].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[4][0].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[4][0].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 5_2
     */
    private void configureButton_5_2() {
        layout[4][1] = (LinearLayout) findViewById(R.id.row_5_2);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_5_2_image);

        layout[4][1].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        }

        layout[4][1].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[4][1].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[4][1].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_5_2.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_5_2.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_5_2.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_5_2.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[4][1].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 5_3
     */
    private void configureButton_5_3() {
        layout[4][2] = (LinearLayout) findViewById(R.id.row_5_3);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_5_3_image);

        layout[4][2].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        }

        layout[4][2].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[4][2].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[4][2].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_5_3.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_5_3.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_5_3.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[4][2].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 5_4
     */
    private void configureButton_5_4() {
        layout[4][3] = (LinearLayout) findViewById(R.id.row_5_4);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_5_4_image);

        layout[4][3].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l1);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        }

        layout[4][3].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[4][3].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[4][3].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_5_4.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_5_4.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_5_4.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_5_4.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[4][3].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 5_5
     */
    private void configureButton_5_5() {
        layout[4][4] = (LinearLayout) findViewById(R.id.row_5_5);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_5_5_image);

        layout[4][4].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        }

        layout[4][4].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[4][4].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[4][4].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_5_5.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_5_5.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_5_5.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_5_5.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[4][4].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 5_6
     */
    private void configureButton_5_6() {
        layout[4][5] = (LinearLayout) findViewById(R.id.row_5_6);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_5_6_image);

        layout[4][5].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        }

        layout[4][5].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[4][5].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[4][5].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[4][5].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 5_7
     */
    private void configureButton_5_7() {
        layout[4][6] = (LinearLayout) findViewById(R.id.row_5_7);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_5_7_image);

        layout[4][6].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        }

        layout[4][6].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[4][6].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[4][6].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_5_7.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_5_7.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_5_7.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_5_7.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[4][6].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 6_1
     */
    private void configureButton_6_1() {
        layout[5][0] = (LinearLayout) findViewById(R.id.row_6_1);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_6_1_image);

        layout[5][0].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        }

        layout[5][0].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[5][0].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[5][0].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[5][0].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 6_2
     */
    private void configureButton_6_2() {
        layout[5][1] = (LinearLayout) findViewById(R.id.row_6_2);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_6_2_image);

        layout[5][1].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l2);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l2);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        }

        layout[5][1].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[5][1].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[5][1].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_6_2.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_6_2.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_6_2.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_6_2.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[5][1].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 6_3
     */
    private void configureButton_6_3() {
        layout[5][2] = (LinearLayout) findViewById(R.id.row_6_3);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_6_3_image);

        layout[5][2].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l1);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        }

        layout[5][2].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[5][2].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[5][2].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_6_3.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_6_3.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_6_3.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_6_3.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[5][2].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 6_4
     */
    private void configureButton_6_4() {
        layout[5][3] = (LinearLayout) findViewById(R.id.row_6_4);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_6_4_image);

        layout[5][3].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        }

        layout[5][3].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[5][3].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[5][3].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_6_4.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_6_4.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_6_4.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_6_4.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[5][3].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 6_5
     */
    private void configureButton_6_5() {
        layout[5][4] = (LinearLayout) findViewById(R.id.row_6_5);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_6_5_image);

        layout[5][4].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        }

        layout[5][4].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[5][4].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[5][4].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_6_5.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_6_5.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_6_5.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[5][4].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 6_6
     */
    private void configureButton_6_6() {
        layout[5][5] = (LinearLayout) findViewById(R.id.row_6_6);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_6_6_image);

        layout[5][5].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        }

        layout[5][5].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[5][5].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[5][5].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_6_6.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[5][5].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 6_7
     */
    private void configureButton_6_7() {
        layout[5][6] = (LinearLayout) findViewById(R.id.row_6_7);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_6_7_image);

        layout[5][6].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        }

        layout[5][6].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[5][6].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[5][6].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_6_7.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_6_7.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_6_7.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_6_7.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[5][6].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 7_1
     */
    private void configureButton_7_1() {
        layout[6][0] = (LinearLayout) findViewById(R.id.row_7_1);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_7_1_image);

        layout[6][0].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        }

        layout[6][0].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[6][0].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[6][0].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[6][0].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 7_2
     */
    private void configureButton_7_2() {
        layout[6][1] = (LinearLayout) findViewById(R.id.row_7_2);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_7_2_image);

        layout[6][1].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        }

        layout[6][1].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[6][1].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[6][1].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_7_2.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_7_2.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_7_2.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[6][1].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 7_3
     */
    private void configureButton_7_3() {
        layout[6][2] = (LinearLayout) findViewById(R.id.row_7_3);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_7_3_image);

        layout[6][2].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        }

        layout[6][2].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[6][2].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[6][2].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_7_3.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_7_3.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_7_3.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_7_3.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[6][2].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 7_4
     */
    private void configureButton_7_4() {
        layout[6][3] = (LinearLayout) findViewById(R.id.row_7_4);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_7_4_image);

        layout[6][3].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        }

        layout[6][3].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[6][3].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[6][3].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_7_4.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[6][3].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 7_5
     */
    private void configureButton_7_5() {
        layout[6][4] = (LinearLayout) findViewById(R.id.row_7_5);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_7_5_image);

        layout[6][4].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        }

        layout[6][4].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[6][4].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[6][4].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_7_5.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_7_5.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_7_5.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_7_5.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[6][4].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 7_6
     */
    private void configureButton_7_6() {
        layout[6][5] = (LinearLayout) findViewById(R.id.row_7_6);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_7_6_image);

        layout[6][5].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        }

        layout[6][5].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[6][5].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[6][5].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[6][5].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 7_7
     */
    private void configureButton_7_7() {
        layout[6][6] = (LinearLayout) findViewById(R.id.row_7_7);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_7_7_image);

        layout[6][6].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        }

        layout[6][6].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[6][6].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[6][6].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_7_7.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_7_7.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_7_7.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_7_7.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[6][6].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 8_1
     */
    private void configureButton_8_1() {
        layout[7][0] = (LinearLayout) findViewById(R.id.row_8_1);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_8_1_image);

        layout[7][0].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        }

        layout[7][0].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[7][0].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[7][0].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[7][0].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 8_2
     */
    private void configureButton_8_2() {
        layout[7][1] = (LinearLayout) findViewById(R.id.row_8_2);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_8_2_image);

        layout[7][1].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        }

        layout[7][1].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[7][1].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[7][1].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_8_2.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_8_2.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_8_2.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_8_2.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[7][1].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 8_3
     */
    private void configureButton_8_3() {
        layout[7][2] = (LinearLayout) findViewById(R.id.row_8_3);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_8_3_image);

        layout[7][2].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        }

        layout[7][2].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[7][2].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[7][2].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_8_3.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_8_3.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_8_3.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[7][2].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 8_4
     */
    private void configureButton_8_4() {
        layout[7][3] = (LinearLayout) findViewById(R.id.row_8_4);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_8_4_image);

        layout[7][3].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        }

        layout[7][3].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[7][3].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[7][3].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_8_4.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_8_4.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_8_4.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[7][3].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 8_5
     */
    private void configureButton_8_5() {
        layout[7][4] = (LinearLayout) findViewById(R.id.row_8_5);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_8_5_image);

        layout[7][4].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        }

        layout[7][4].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[7][4].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[7][4].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_8_5.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_8_5.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_8_5.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[7][4].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 8_6
     */
    private void configureButton_8_6() {
        layout[7][5] = (LinearLayout) findViewById(R.id.row_8_6);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_8_6_image);

        layout[7][5].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        }

        layout[7][5].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[7][5].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[7][5].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[7][5].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 8_7
     */
    private void configureButton_8_7() {
        layout[7][6] = (LinearLayout) findViewById(R.id.row_8_7);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_8_7_image);

        layout[7][6].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        }

        layout[7][6].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[7][6].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[7][6].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_8_7.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_8_7.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_8_7.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_8_7.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[7][6].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 9_1
     */
    private void configureButton_9_1() {
        layout[8][0] = (LinearLayout) findViewById(R.id.row_9_1);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_9_1_image);

        layout[8][0].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        }

        layout[8][0].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[8][0].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[8][0].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[8][0].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 9_2
     */
    private void configureButton_9_2() {
        layout[8][1] = (LinearLayout) findViewById(R.id.row_9_2);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_9_2_image);

        layout[8][1].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        }

        layout[8][1].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[8][1].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[8][1].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_9_2.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_9_2.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_9_2.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_9_2.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[8][1].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 9_3
     */
    private void configureButton_9_3() {
        layout[8][2] = (LinearLayout) findViewById(R.id.row_9_3);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_9_3_image);

        layout[8][2].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        }

        layout[8][2].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[8][2].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[8][2].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_9_3.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[8][2].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 9_4
     */
    private void configureButton_9_4() {
        layout[8][3] = (LinearLayout) findViewById(R.id.row_9_4);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_9_4_image);

        layout[8][3].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        }

        layout[8][3].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[8][3].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[8][3].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_9_4.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_9_4.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_9_4.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_9_4.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[8][3].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 9_5
     */
    private void configureButton_9_5() {
        layout[8][4] = (LinearLayout) findViewById(R.id.row_9_5);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_9_5_image);

        layout[8][4].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        }

        layout[8][4].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[8][4].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[8][4].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_9_5.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_9_5.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_9_5.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[8][4].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 9_6
     */
    private void configureButton_9_6() {
        layout[8][5] = (LinearLayout) findViewById(R.id.row_9_6);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_9_6_image);

        layout[8][5].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        }

        layout[8][5].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[8][5].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[8][5].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[8][5].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 9_7
     */
    private void configureButton_9_7() {
        layout[8][6] = (LinearLayout) findViewById(R.id.row_9_7);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_9_7_image);

        layout[8][6].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        }

        layout[8][6].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[8][6].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[8][6].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_9_7.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_9_7.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_9_7.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_9_7.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[8][6].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 10_1
     */
    private void configureButton_10_1() {
        layout[9][0] = (LinearLayout) findViewById(R.id.row_10_1);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_10_1_image);

        layout[9][0].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        }

        layout[9][0].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[9][0].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[9][0].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[9][0].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 10_2
     */
    private void configureButton_10_2() {
        layout[9][1] = (LinearLayout) findViewById(R.id.row_10_2);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_10_2_image);

        layout[9][1].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        }

        layout[9][1].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[9][1].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[9][1].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_10_2.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_10_2.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_10_2.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[9][1].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 10_3
     */
    private void configureButton_10_3() {
        layout[9][2] = (LinearLayout) findViewById(R.id.row_10_3);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_10_3_image);

        layout[9][2].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        }

        layout[9][2].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[9][2].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[9][2].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_10_3.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_10_3.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_10_3.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), Adv_10_3.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[9][2].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 10_4
     */
    private void configureButton_10_4() {
        layout[9][3] = (LinearLayout) findViewById(R.id.row_10_4);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_10_4_image);

        layout[9][3].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        }

        layout[9][3].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[9][3].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[9][3].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), Com_10_4.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[9][3].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 10_5
     */
    private void configureButton_10_5() {
        layout[9][4] = (LinearLayout) findViewById(R.id.row_10_5);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_10_5_image);

        layout[9][4].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        }

        layout[9][4].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[9][4].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[9][4].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_10_5.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_10_5.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[9][4].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 10_6
     */
    private void configureButton_10_6() {
        layout[9][5] = (LinearLayout) findViewById(R.id.row_10_6);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_10_6_image);

        layout[9][5].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        }

        layout[9][5].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[9][5].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[9][5].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[9][5].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 10_7
     */
    private void configureButton_10_7() {
        layout[9][6] = (LinearLayout) findViewById(R.id.row_10_7);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_10_7_image);

        layout[9][6].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        }

        layout[9][6].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[9][6].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[9][6].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_10_7.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_10_7.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), RaceDayActivity.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), RaceDayActivity.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[9][6].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 11_1
     */
    private void configureButton_11_1() {
        layout[10][0] = (LinearLayout) findViewById(R.id.row_11_1);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_11_1_image);

        layout[10][0].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        }

        layout[10][0].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[10][0].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[10][0].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[10][0].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 11_2
     */
    private void configureButton_11_2() {
        layout[10][1] = (LinearLayout) findViewById(R.id.row_11_2);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_11_2_image);

        layout[10][1].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        }

        layout[10][1].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[10][1].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[10][1].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_11_2.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[10][1].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 11_3
     */
    private void configureButton_11_3() {
        layout[10][2] = (LinearLayout) findViewById(R.id.row_11_3);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_11_3_image);

        layout[10][2].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        }

        layout[10][2].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[10][2].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[10][2].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_11_3.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[10][2].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 11_4
     */
    private void configureButton_11_4() {
        layout[10][3] = (LinearLayout) findViewById(R.id.row_11_4);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_11_4_image);

        layout[10][3].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        }

        layout[10][3].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[10][3].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[10][3].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_11_4.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[10][3].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 11_5
     */
    private void configureButton_11_5() {
        layout[10][4] = (LinearLayout) findViewById(R.id.row_11_5);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_11_5_image);

        layout[10][4].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        }

        layout[10][4].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[10][4].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[10][4].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_11_5.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_11_5.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[10][4].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 11_6
     */
    private void configureButton_11_6() {
        layout[10][5] = (LinearLayout) findViewById(R.id.row_11_6);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_11_6_image);

        layout[10][5].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        }

        layout[10][5].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[10][5].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[10][5].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[10][5].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 11_7
     */
    private void configureButton_11_7() {
        layout[10][6] = (LinearLayout) findViewById(R.id.row_11_7);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_11_7_image);

        layout[10][6].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        }

        layout[10][6].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[10][6].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[10][6].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_11_7.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_11_7.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[10][6].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 12_1
     */
    private void configureButton_12_1() {
        layout[11][0] = (LinearLayout) findViewById(R.id.row_12_1);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_12_1_image);

        layout[11][0].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        }

        layout[11][0].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[11][0].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[11][0].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[11][0].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 12_2
     */
    private void configureButton_12_2() {
        layout[11][1] = (LinearLayout) findViewById(R.id.row_12_2);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_12_2_image);

        layout[11][1].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        }

        layout[11][1].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[11][1].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[11][1].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_12_2.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[11][1].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 12_3
     */
    private void configureButton_12_3() {
        layout[11][2] = (LinearLayout) findViewById(R.id.row_12_3);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_12_3_image);

        layout[11][2].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        }

        layout[11][2].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[11][2].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[11][2].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_12_3.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_12_3.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[11][2].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 12_4
     */
    private void configureButton_12_4() {
        layout[11][3] = (LinearLayout) findViewById(R.id.row_12_4);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_12_4_image);

        layout[11][3].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l3);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        }

        layout[11][3].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[11][3].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[11][3].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), Beg_12_4.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), Workout_12_4.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[11][3].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 12_5
     */
    private void configureButton_12_5() {
        layout[11][4] = (LinearLayout) findViewById(R.id.row_12_5);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_12_5_image);

        layout[11][4].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        }

        layout[11][4].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[11][4].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[11][4].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[11][4].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 12_6
     */
    private void configureButton_12_6() {
        layout[11][5] = (LinearLayout) findViewById(R.id.row_12_6);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_12_6_image);

        layout[11][5].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        }

        layout[11][5].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[11][5].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[11][5].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[11][5].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Configures the click listener for workout button 12_7
     */
    private void configureButton_12_7() {
        layout[11][6] = (LinearLayout) findViewById(R.id.row_12_7);

        ImageView difficulty_image = (ImageView) findViewById(R.id.row_12_7_image);

        layout[11][6].requestFocus();

        if (difficulty[BEGINNER]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[INTERMEDIATE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_l4);
        } else if (difficulty[COMPETITIVE]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        } else if (difficulty[ADVANCED]) {
            difficulty_image.setImageResource(R.drawable.running_parts_rest);
        }

        layout[11][6].setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        layout[11][6].setBackgroundColor(0xFFe1e1e1);
                        break;

                    case MotionEvent.ACTION_UP:
                        layout[11][6].setBackgroundColor(Color.WHITE);

                        if (difficulty[BEGINNER]) {
                            startActivity(new Intent(getApplicationContext(), RaceDayActivity.class));
                        } else if (difficulty[INTERMEDIATE]) {
                            startActivity(new Intent(getApplicationContext(), RaceDayActivity.class));
                        } else if (difficulty[COMPETITIVE]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        } else if (difficulty[ADVANCED]) {
                            startActivity(new Intent(getApplicationContext(), RestDayActivity.class));
                        }
                        
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                        break;

                    case MotionEvent.ACTION_CANCEL:
                        layout[11][6].setBackgroundColor(Color.WHITE);
                        break;
                }
                return true;
            }
        });
    }
}
