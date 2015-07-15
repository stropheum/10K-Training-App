package com.fitivity.tenktraining;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


public abstract class SixPartActivity extends ActionBarActivity {

    // Allow 15 seconds of error for time calculations
    final double MILE_TIME_ERROR = 0.5;

    final int PACE_UPDATE_INTERVAL = 1;

    //final TextView pace = (TextView) findViewById(R.id.paceView);

    // TextViews for displaying goal paces and workout summaries
    TextView mainTitle;
    TextView secondaryTitle;
    TextView nextTitle;

    protected final int MINUTE           = 61;
    protected final int MINUTES          = 62;
    protected final int SECOND           = 63;
    protected final int SECONDS          = 64;
    protected final int MINUTE_PER_MILE  = 65;
    protected final int PACE             = 66;

    // Goal mile times for each part
    protected double PART_ONE_GOAL_PACE;
    protected double PART_TWO_GOAL_PACE;
    protected double PART_THREE_GOAL_PACE;
    protected double PART_FOUR_GOAL_PACE;
    protected double PART_FIVE_GOAL_PACE;
    protected double PART_SIX_GOAL_PACE;

    // Duration for each part in milliseconds
    protected int PART_ONE_DURATION;
    protected int PART_TWO_DURATION;
    protected int PART_THREE_DURATION;
    protected int PART_FOUR_DURATION;
    protected int PART_FIVE_DURATION;
    protected int PART_SIX_DURATION;

    // Secondary titles for actionbar to set at each part
    // Format: "00:00 min/mile"
    protected String PART_ONE_SECONDARY_TITLE;
    protected String PART_TWO_SECONDARY_TITLE;
    protected String PART_THREE_SECONDARY_TITLE;
    protected String PART_FOUR_SECONDARY_TITLE;
    protected String PART_FIVE_SECONDARY_TITLE;
    protected String PART_SIX_SECONDARY_TITLE;

    public SpeedCalculationService speedCalculator;
    protected boolean isBound = false;

    protected double currentPace, goalPace;
    protected double paceSum, paceAverage;
    protected double speed;
    protected double distance = 0;

    // Tracks the time a part starts and how long it has been running for
    protected double timeStart, timeElapsed;

    protected CountDownTimer partTimer;
    protected long timeRemaining;
    protected boolean isPaused;

    // Tracks the start time and elapsed time of individual parts
    protected double partTimeStart, partTimeElapsed;

    protected int currentPart;

    protected int tickCounter; // Counts the number of ticks on current part

    // Value to determine if the part has run for the first time
    protected boolean partOneFirstRun, partTwoFirstRun, partThreeFirstRun,
            partFourFirstRun, partFiveFirstRun, partSixFirstRun;

    // Value to track the first tick of each timer
    protected boolean partOneFirstTick, partTwoFirstTick, partThreeFirstTick,
            partFourFirstTick, partFiveFirstTick, partSixFirstTick;

    // Tracks if "perfect pace" was said once so it doesn't repeat
    protected boolean saidPerfectOnce;

    protected String paceText;
    protected Intent i;

    protected Vibrator vibrator;

    protected ImageButton pauseButton;
    protected ImageButton backButton, nextButton;

    protected ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_six_part);

        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Disable screen timeout while workout is active
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        i = new Intent(this, SpeedCalculationService.class);

        // Enable values to track the first call to each part to initialize CountDownTimer values
        partOneFirstRun   = true;
        partTwoFirstRun   = true;
        partThreeFirstRun = true;
        partFourFirstRun  = true;
        partFiveFirstRun  = true;
        partSixFirstRun   = true;

        saidPerfectOnce = false;

        paceSum = 0.0;

        // Initialize loading image
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        isPaused = true;
        pauseButton = (ImageButton) findViewById(R.id.pauseButton);
        pauseButton.setEnabled(false);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handlePauseClick();
            }
        });

        backButton = (ImageButton) findViewById(R.id.partLeftButton);
        backButton.setEnabled(false);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleBackButtonClick();
            }
        });

        nextButton = (ImageButton) findViewById(R.id.partRightButton);
        nextButton.setEnabled(false);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleNextButtonClick();
            }
        });

        mainTitle = (TextView) findViewById(R.id.mainTitle);
        secondaryTitle = (TextView) findViewById(R.id.secondaryTitle);
        secondaryTitle.setText(PART_ONE_SECONDARY_TITLE);

        // Starts the service for calculating user's speed
        bindService(i, speedConnection, Context.BIND_AUTO_CREATE);
        vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        String paceText = "Waiting for GPS";
        updatePaceText(paceText);
        currentPart = 1; // Tracks currently active part
        timeRemaining = PART_ONE_DURATION;
        tickCounter = 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_six_part, menu);
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

        if (id == 16908332) {
            stopService(new Intent(SixPartActivity.this, SpeedCalculationService.class));
            stopService(i);
            unbindService(speedConnection);
            this.finish();
            overridePendingTransition(R.anim.slide_out_to_right, R.anim.slide_in_from_left);
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
    public void onBackPressed() {
        // Terminate the speed calculation service
        stopService(new Intent(SixPartActivity.this, SpeedCalculationService.class));
        unbindService(speedConnection);
        finish();
        overridePendingTransition(R.anim.slide_out_to_right, R.anim.slide_in_from_left);
        return;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (partTimer != null) {
            partTimer.cancel();
        }
    }

    /**
     * Updates the display to show the current speed
     *
     * @param speed The current speed of the user
     */
//    private void updateSpeed(double speed) {
//        final TextView speedVal = (TextView) findViewById(R.id.SpeedVal);
//        speedVal.setText(String.format("%.2f", speed));
//    }

    /**
     * Updates the current estimated mile time
     *
     * @param currentPace User's current mile time
     */
    private void updateCurrentPace(double currentPace) {
        int minutes = (int) currentPace;
        int seconds = (int) (((currentPace * 100) % 100) * 0.6);
        if (minutes > 99) {
            minutes = 0;
            seconds = 0;
        }
        final TextView emtVal = (TextView) findViewById(R.id.emtVal);
        emtVal.setText(String.format("%02d:%02d", minutes, seconds));
    }

    /**
     * Updates the current goal mile time
     *
     * @param goalPace New goal mile time
     */
    private void updateGoalPace(double goalPace) {
        int minutes = (int) goalPace;
        int seconds = (int) (((goalPace * 100) % 100) * 0.6);
        final TextView gmtVal = (TextView) findViewById(R.id.gmtVal);
        gmtVal.setText(String.format("%d:%02d", minutes, seconds));
    }

    /**
     * Updates the current pace text
     *
     * @param paceText indicator for user;s current speed in relation to goal time
     */
    private void updatePaceText(String paceText) {
        TextView pace = (TextView) findViewById(R.id.paceView);
        pace.setText(paceText);
    }

    /**
     * Updates pace color according to current pace
     *
     * @param color color to change the text to
     */
    private void updatePaceColor(String color) {
        TextView pace = (TextView) findViewById(R.id.paceView);
        pace.setTextColor(Color.parseColor(color));
    }

    /**
     * Updates curent distance traveled
     *
     * @param distance The current overall distance traveled
     */
    private void updateDistance(double distance) {
        final TextView distanceText = (TextView) findViewById(R.id.distanceVal);
        distanceText.setText(String.format("%.2f", distance));
    }

    /**
     * Checks current pace and assigns appropriate text and color
     */
    private void paceAlert() {
        timeStart = System.currentTimeMillis(); // Reset alert interval
        String paceColor;
        MediaPlayer player;

        if (currentPace > goalPace + MILE_TIME_ERROR) {
            paceText = "Speed up";
            paceColor = "#52be7f";//Green
            long[] pattern = {0, 200, 200, 200, 200, 200};
            vibrator.vibrate(pattern, -1);
            player = MediaPlayer.create(this, R.raw.speed_up);
            player.start();
            saidPerfectOnce = false; // Reset perfect alert
        } else if (currentPace < goalPace - MILE_TIME_ERROR) {
            paceText = "Slow Down";
            paceColor = "#e74c3c";//Red
            vibrator.vibrate(1000);
            player = MediaPlayer.create(this, R.raw.slow_down);
            player.start();
            saidPerfectOnce = false; // Reset perfect alert
        } else {
            paceText = "Perfect Pace!";
            paceColor = "#3498db";//Blue
            player = MediaPlayer.create(this, R.raw.perfect_pace);
            if (!saidPerfectOnce) {
                player.start();
            }
            saidPerfectOnce = true; // Don't repeat multiple consecutive perfect pace alerts
        }
        updatePaceText(paceText);
        updatePaceColor(paceColor);
    }

    /**
     * Updates the timer display on current workout to reflect total elapsed time
     */
    private void updateTime() {
        double time = timeRemaining / 1000; // Store remaining time in seconds

        int minutes = (int) time / 60;
        int seconds = (int) time % 60;

        final TextView timeView = (TextView) findViewById(R.id.timeLabel);
        timeView.setText(String.format("%02d:%02d", minutes, seconds));
    }

    /**
     * Updates the timer display specifically for initial countdown timer
     */
    private void updateCountdownTime() {
        double time = timeRemaining / 1000;

        int minutes = (int) (time / 60);
        int seconds;

        if (timeRemaining > 2000) {
            seconds = 3;
        } else if (timeRemaining > 1000) {
            seconds = 2;
        } else {
            seconds = 1;
        }


        final TextView timeView = (TextView) findViewById(R.id.timeLabel);
        timeView.setText(String.format("%02d:%02d", minutes, seconds));
    }

    ServiceConnection speedConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            SpeedCalculationService.SpeedCalculationBinder binder = (SpeedCalculationService.SpeedCalculationBinder) service;
            isBound = true;
            speedCalculator = binder.getService();

            waitForService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    /**
     * Enables start button when GPS signal is found
     */
    public void waitForService() {
        Timer t = new Timer();

        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!speedCalculator.searchingForSignal()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.GONE); // Disable progress bar
                            pauseButton.setEnabled(true);
                            pauseButton.setBackgroundResource(R.drawable.play_button);
                            final TextView paceText = (TextView) findViewById(R.id.paceView);
                            updatePaceColor("#3498db");
                            paceText.setText("GPS Found");
                        }
                    });
                    cancel();
                }
            }
        }, 0, 100);
    }

    /**
     * Method called when pause/play button is pressed
     */
    private void handlePauseClick() {
        if (isPaused) {
            tickCounter--; // handle off-by-one error with pausing
            pauseButton.setBackgroundResource(R.drawable.pause_button);
            switch (currentPart) {
                case 1:
                    if (partOneFirstRun) {
                        initialCountdownBegin();
                    } else {
                        partOneBegin();
                    }
                    break;
                case 2:
                    partTwoBegin();
                    break;
                case 3:
                    partThreeBegin();
                    break;
                case 4:
                    partFourBegin();
                    break;
                case 5:
                    partFiveBegin();
                    break;
                case 6:
                    partSixBegin();
                    break;
            }
            isPaused = false;
        } else {
            pauseButton.setBackgroundResource(R.drawable.play_button);
            backButton.setEnabled(false); // Disable part skipping when app is paused
            nextButton.setEnabled(false); // Disable part skipping when app is paused
            partTimer.cancel();
            isPaused = true;
        }
    }

    /**
     * Method called when "back" button is clicked
     */
    private void handleBackButtonClick() {

        partTimer.cancel();

        switch (currentPart) {
            case 1:
                // Do nothing
                break;
            case 2:
                partOneFirstRun = true;
                timeRemaining = PART_ONE_DURATION;
                partOneBegin();
                break;
            case 3:
                partTwoFirstRun = true;
                timeRemaining = PART_TWO_DURATION;
                partTwoBegin();
                break;
            case 4:
                partThreeFirstRun = true;
                timeRemaining = PART_THREE_DURATION;
                partThreeBegin();
                break;
            case 5:
                partFourFirstRun = true;
                timeRemaining = PART_FOUR_DURATION;
                partFourBegin();
                break;
            case 6:
                partFiveFirstRun = true;
                timeRemaining = PART_FIVE_DURATION;
                partFiveBegin();
                break;
        }
    }

    /**
     * Method called when "next" button is clicked
     */
    private void handleNextButtonClick() {

        partTimer.cancel();

        switch (currentPart) {
            case 1:
                partTwoFirstRun = true;
                timeRemaining = PART_TWO_DURATION;
                partTwoBegin();
                break;
            case 2:
                partThreeFirstRun = true;
                timeRemaining = PART_THREE_DURATION;
                partThreeBegin();
                break;
            case 3:
                partFourFirstRun = true;
                timeRemaining = PART_FOUR_DURATION;
                partFourBegin();
                break;
            case 4:
                partFiveFirstRun = true;
                timeRemaining = PART_FIVE_DURATION;
                partFiveBegin();
                break;
            case 5:
                partSixFirstRun = true;
                timeRemaining = PART_SIX_DURATION;
                partSixBegin();
                break;
            case 6:
                // Do nothing
                break;
        }
    }

    /**
     * Plays a sound file associated with the number passed in
     * @param n the number associated with the sound file played
     */
    private MediaPlayer setSound(int n) {
        MediaPlayer sound;
        switch (n) {
            case 0:
                sound = MediaPlayer.create(this, R.raw.zero);
                break;
            case 1:
                sound = MediaPlayer.create(this, R.raw.one);
                break;
            case 2:
                sound = MediaPlayer.create(this, R.raw.two);
                break;
            case 3:
                sound = MediaPlayer.create(this, R.raw.three);
                break;
            case 4:
                sound = MediaPlayer.create(this, R.raw.four);
                break;
            case 5:
                sound = MediaPlayer.create(this, R.raw.five);
                break;
            case 6:
                sound = MediaPlayer.create(this, R.raw.six);
                break;
            case 7:
                sound = MediaPlayer.create(this, R.raw.seven);
                break;
            case 8:
                sound = MediaPlayer.create(this, R.raw.eight);
                break;
            case 9:
                sound = MediaPlayer.create(this, R.raw.nine);
                break;
            case 10:
                sound = MediaPlayer.create(this, R.raw.ten);
                break;
            case 11:
                sound = MediaPlayer.create(this, R.raw.eleven);
                break;
            case 12:
                sound = MediaPlayer.create(this, R.raw.twelve);
                break;
            case 13:
                sound = MediaPlayer.create(this, R.raw.thirteen);
                break;
            case 14:
                sound = MediaPlayer.create(this, R.raw.fourteen);
                break;
            case 15:
                sound = MediaPlayer.create(this, R.raw.fifteen);
                break;
            case 16:
                sound = MediaPlayer.create(this, R.raw.sixteen);
                break;
            case 17:
                sound = MediaPlayer.create(this, R.raw.seventeen);
                break;
            case 18:
                sound = MediaPlayer.create(this, R.raw.eighteen);
                break;
            case 19:
                sound = MediaPlayer.create(this, R.raw.nineteen);
                break;
            case 20:
                sound = MediaPlayer.create(this, R.raw.twenty);
                break;
            case 21:
                sound = MediaPlayer.create(this, R.raw.twenty_one);
                break;
            case 22:
                sound = MediaPlayer.create(this, R.raw.twenty_two);
                break;
            case 23:
                sound = MediaPlayer.create(this, R.raw.twenty_three);
                break;
            case 24:
                sound = MediaPlayer.create(this, R.raw.twenty_four);
                break;
            case 25:
                sound = MediaPlayer.create(this, R.raw.twenty_five);
                break;
            case 26:
                sound = MediaPlayer.create(this, R.raw.twenty_six);
                break;
            case 27:
                sound = MediaPlayer.create(this, R.raw.twenty_seven);
                break;
            case 28:
                sound = MediaPlayer.create(this, R.raw.twenty_eight);
                break;
            case 29:
                sound = MediaPlayer.create(this, R.raw.twenty_nine);
                break;
            case 30:
                sound = MediaPlayer.create(this, R.raw.thirty);
                break;
            case 31:
                sound = MediaPlayer.create(this, R.raw.thirty_one);
                break;
            case 32:
                sound = MediaPlayer.create(this, R.raw.thirty_two);
                break;
            case 33:
                sound = MediaPlayer.create(this, R.raw.thirty_three);
                break;
            case 34:
                sound = MediaPlayer.create(this, R.raw.thirty_four);
                break;
            case 35:
                sound = MediaPlayer.create(this, R.raw.thirty_five);
                break;
            case 36:
                sound = MediaPlayer.create(this, R.raw.thirty_six);
                break;
            case 37:
                sound = MediaPlayer.create(this, R.raw.thirty_seven);
                break;
            case 38:
                sound = MediaPlayer.create(this, R.raw.thirty_eight);
                break;
            case 39:
                sound = MediaPlayer.create(this, R.raw.thirty_nine);
                break;
            case 40:
                sound = MediaPlayer.create(this, R.raw.fourty);
                break;
            case 41:
                sound = MediaPlayer.create(this, R.raw.fourty_one);
                break;
            case 42:
                sound = MediaPlayer.create(this, R.raw.fourty_two);
                break;
            case 43:
                sound = MediaPlayer.create(this, R.raw.fourty_three);
                break;
            case 44:
                sound = MediaPlayer.create(this, R.raw.fourty_four);
                break;
            case 45:
                sound = MediaPlayer.create(this, R.raw.fourty_five);
                break;
            case 46:
                sound = MediaPlayer.create(this, R.raw.fourty_six);
                break;
            case 47:
                sound = MediaPlayer.create(this, R.raw.fourty_seven);
                break;
            case 48:
                sound = MediaPlayer.create(this, R.raw.fourty_eight);
                break;
            case 49:
                sound = MediaPlayer.create(this, R.raw.fourty_nine);
                break;
            case 50:
                sound = MediaPlayer.create(this, R.raw.fifty);
                break;
            case 51:
                sound = MediaPlayer.create(this, R.raw.fifty_one);
                break;
            case 52:
                sound = MediaPlayer.create(this, R.raw.fifty_two);
                break;
            case 53:
                sound = MediaPlayer.create(this, R.raw.fifty_three);
                break;
            case 54:
                sound = MediaPlayer.create(this, R.raw.fifty_four);
                break;
            case 55:
                sound = MediaPlayer.create(this, R.raw.fifty_five);
                break;
            case 56:
                sound = MediaPlayer.create(this, R.raw.fifty_six);
                break;
            case 57:
                sound = MediaPlayer.create(this, R.raw.fifty_seven);
                break;
            case 58:
                sound = MediaPlayer.create(this, R.raw.fifty_eight);
                break;
            case 59:
                sound = MediaPlayer.create(this, R.raw.fifty_nine);
                break;
            case 60:
                sound = MediaPlayer.create(this, R.raw.sixty);
                break;
            case MINUTE:
                sound = MediaPlayer.create(this, R.raw.minute);
                break;
            case MINUTES:
                sound = MediaPlayer.create(this, R.raw.minutes);
                break;
            case SECOND:
                sound = MediaPlayer.create(this, R.raw.second);
                break;
            case SECONDS:
                sound = MediaPlayer.create(this, R.raw.seconds);
                break;
            case MINUTE_PER_MILE:
                sound = MediaPlayer.create(this, R.raw.minute_per_mile);
                break;
            case PACE:
                sound = MediaPlayer.create(this, R.raw.pace);
                break;
            default:
                sound = MediaPlayer.create(this, R.raw.zero);
                break;
        }
        return sound;
    }

    /**
     * Tells user what pace to run
     */
    private void announcePace(double pace) {
        MediaPlayer phrase[] = new MediaPlayer[5];
        for (int i = 0; i < phrase.length; i++) {
            phrase[i] = null;
        }

        int minute = (int) pace;
        int second = (int) ((pace - minute) * 60);

        // Initialize minute portion of phrase
        phrase[0] = setSound(minute);
        phrase[1] = setSound(MINUTE);

        // Initialize second portion of phrase
        phrase[2] = setSound(second);
        phrase[3] = setSound(SECOND);

        // Initialize final portion of phrase
        phrase[4] = setSound(PACE);

        // Set sound files in phrase to play concurrently
        for (int i = 0; i < phrase.length - 1; i++) {
            phrase[i].setNextMediaPlayer(phrase[i + 1]);
        }

        phrase[0].start();

    }

    /**
     * Begins initial countdown for workout
     */
    public void initialCountdownBegin() {
        pauseButton.setEnabled(false);
        timeRemaining = 3000;
        partTimer = new CountDownTimer(timeRemaining, 500) {
            @Override
            public void onTick(long l) {
                timeRemaining = l;
                updateCountdownTime();
            }

            @Override
            public void onFinish() {
                pauseButton.setEnabled(true);
                updatePaceText("Begin!");
                partOneBegin();
            }
        }.start();

    }

    /**
     * Method called when Speed Calculation Service is successfully bound
     */
    public void partOneBegin() {

        backButton.setEnabled(false); // Disable back button when first part begins
        nextButton.setEnabled(true); // Enable next button when first part begins

        speedCalculator.resetDistance();

        if (partOneFirstRun) {
            timeRemaining = PART_ONE_DURATION;
            tickCounter = 0;
            announcePace(PART_ONE_GOAL_PACE);
            partOneFirstRun = false;
        }

        speedCalculator.resetValues();
        currentPart = 1;

        // Update titles
        secondaryTitle.setText(PART_ONE_SECONDARY_TITLE);

        final RadioButton partButton1 = (RadioButton) findViewById(R.id.radioButton1);
        partButton1.setChecked(true);

        partOneFirstTick = true;

        partTimer = new CountDownTimer(timeRemaining, 1000) {
            @Override
            public void onTick(long l) {
                if (partOneFirstTick) {
                    partTimeStart = System.currentTimeMillis();

                    updateTime();

                    goalPace = PART_ONE_GOAL_PACE;
                    updateGoalPace(goalPace);
                    paceSum = 0.0;
                    paceAverage = 0.0;

                    distance = 0.0;
                    updateDistance(distance);

                    partOneFirstTick = false;
                }
                tickCounter++; // Track number of ticks on current part

                timeRemaining = l; // Store remaining time in the current part

                // Tracks the elapsed time since last alert
                timeElapsed = System.currentTimeMillis() - timeStart;
                updateTime();

                // Tracks the total elapsed time of the workout part
                partTimeElapsed = System.currentTimeMillis() - partTimeStart;

                speed = speedCalculator.getCurrentSpeed();
                //updateSpeed(speed);

                double lastPace = currentPace;
                currentPace = 60.0 / speed;

                if (currentPace > 30.0) {
                    currentPace = lastPace;
                }

                // Average current pace to current average
//                if (Double.compare(currentPace, Double.NaN) != 0) {
//                    paceSum += currentPace;
//                    if (Double.compare(paceSum, Double.NaN) == 0) {
//                        paceSum = 0.0;
//                    }
//                    paceAverage = paceSum / tickCounter;
//                }

                if (tickCounter % PACE_UPDATE_INTERVAL == 0) {
                    updateCurrentPace(currentPace);
                }

                distance = speedCalculator.getCurrentDistance();
                updateDistance(distance);

                if (tickCounter % 10 == 0) {// calls pace alert every 10 seconds
                    paceAlert();
                }
            }

            @Override
            public void onFinish() {
                partTwoBegin();
            }

        }.start();
    }

    /**
     * Method called when Speed Calculation Service is successfully bound
     */
    public void partTwoBegin() {

        backButton.setEnabled(true); // Enable back button when second part begins
        nextButton.setEnabled(true); // Enable next button when second part begins

        speedCalculator.resetDistance();

        if (partTwoFirstRun) {
            timeRemaining = PART_TWO_DURATION;
            tickCounter = 0;
            announcePace(PART_TWO_GOAL_PACE);
            partTwoFirstRun = false;
        }

        speedCalculator.resetValues();
        currentPart = 2;

        // Update titles
        secondaryTitle.setText(PART_TWO_SECONDARY_TITLE);

        final RadioButton partButton2 = (RadioButton) findViewById(R.id.radioButton2);
        partButton2.setChecked(true);

        partTwoFirstTick = true;

        partTimer = new CountDownTimer(timeRemaining, 1000) {
            @Override
            public void onTick(long l) {
                if (partTwoFirstTick) {
                    partTimeStart = System.currentTimeMillis();

                    updateTime();

                    goalPace = PART_TWO_GOAL_PACE;
                    updateGoalPace(goalPace);
                    paceSum = 0.0;
                    paceAverage = 0.0;

                    distance = 0.0;
                    updateDistance(distance);

                    partTwoFirstTick = false;
                }
                tickCounter++; // Track number of ticks on current part

                timeRemaining = l; // Store remaining time in the current part

                // Tracks the elapsed time since last alert
                timeElapsed = System.currentTimeMillis() - timeStart;
                updateTime();

                // Tracks the total elapsed time of the workout part
                partTimeElapsed = System.currentTimeMillis() - partTimeStart;

                speed = speedCalculator.getCurrentSpeed();
                //updateSpeed(speed);

                double lastPace = currentPace;
                currentPace = 60.0 / speed;

                if (currentPace > 30.0) {
                    currentPace = lastPace;
                }

                // Average current pace to current average
//                if (Double.compare(currentPace, Double.NaN) != 0) {
//                    paceSum += currentPace;
//                    if (Double.compare(paceSum, Double.NaN) == 0) {
//                        paceSum = 0.0;
//                    }
//                    paceAverage = paceSum / tickCounter;
//                }

                if (tickCounter % PACE_UPDATE_INTERVAL == 0) {
                    updateCurrentPace(currentPace);
                }

                distance = speedCalculator.getCurrentDistance();
                updateDistance(distance);

                if (tickCounter % 10 == 0) {// calls pace alert every 10 seconds
                    paceAlert();
                }
            }

            @Override
            public void onFinish() {
                partThreeBegin();
            }

        }.start();
    }

    /**
     * Method called when Speed Calculation Service is successfully bound
     */
    public void partThreeBegin() {

        backButton.setEnabled(true); // Enable back button when third part begins
        nextButton.setEnabled(true); // Enable next button when third part begins

        speedCalculator.resetDistance();

        if (partThreeFirstRun) {
            timeRemaining = PART_THREE_DURATION;
            tickCounter = 0;
            announcePace(PART_THREE_GOAL_PACE);
            partThreeFirstRun = false;
        }

        speedCalculator.resetValues();
        currentPart = 3;

        // Update titles
        secondaryTitle.setText(PART_THREE_SECONDARY_TITLE);

        final RadioButton partButton3 = (RadioButton) findViewById(R.id.radioButton3);
        partButton3.setChecked(true);

        partThreeFirstTick = true;

        partTimer = new CountDownTimer(timeRemaining, 1000) {
            @Override
            public void onTick(long l) {
                if (partThreeFirstTick) {
                    partTimeStart = System.currentTimeMillis();

                    updateTime();

                    goalPace = PART_THREE_GOAL_PACE;
                    updateGoalPace(goalPace);
                    paceSum = 0.0;
                    paceAverage = 0.0;

                    distance = 0.0;
                    updateDistance(distance);

                    partThreeFirstTick = false;
                }
                tickCounter++; // Track number of ticks on current part

                timeRemaining = l; // Store remaining time in the current part

                // Tracks the elapsed time since last alert
                timeElapsed = System.currentTimeMillis() - timeStart;
                updateTime();

                // Tracks the total elapsed time of the workout part
                partTimeElapsed = System.currentTimeMillis() - partTimeStart;

                speed = speedCalculator.getCurrentSpeed();
                //updateSpeed(speed);

                double lastPace = currentPace;
                currentPace = 60.0 / speed;

                if (currentPace > 30.0) {
                    currentPace = lastPace;
                }

                // Average current pace to current average
//                if (Double.compare(currentPace, Double.NaN) != 0) {
//                    paceSum += currentPace;
//                    if (Double.compare(paceSum, Double.NaN) == 0) {
//                        paceSum = 0.0;
//                    }
//                    paceAverage = paceSum / tickCounter;
//                }

                if (tickCounter % PACE_UPDATE_INTERVAL == 0) {
                    updateCurrentPace(currentPace);
                }

                distance = speedCalculator.getCurrentDistance();
                updateDistance(distance);

                if (tickCounter % 10 == 0) {// calls pace alert every 10 seconds
                    paceAlert();
                }
            }

            @Override
            public void onFinish() {
                partFourBegin();
            }

        }.start();
    }

    /**
     * Method called when Speed Calculation Service is successfully bound
     */
    public void partFourBegin() {

        backButton.setEnabled(true); // Enable back button when fourth part begins
        nextButton.setEnabled(true); // Enable next button when fourth part begins

        speedCalculator.resetDistance();

        if (partFourFirstRun) {
            timeRemaining = PART_FOUR_DURATION;
            tickCounter = 0;
            announcePace(PART_FOUR_GOAL_PACE);
            partFourFirstRun = false;
        }

        speedCalculator.resetValues();
        currentPart = 4;

        // Update titles
        secondaryTitle.setText(PART_FOUR_SECONDARY_TITLE);

        final RadioButton partButton4 = (RadioButton) findViewById(R.id.radioButton4);
        partButton4.setChecked(true);

        partFourFirstTick = true;

        partTimer = new CountDownTimer(timeRemaining, 1000) {
            @Override
            public void onTick(long l) {
                if (partFourFirstTick) {
                    partTimeStart = System.currentTimeMillis();

                    updateTime();

                    goalPace = PART_FOUR_GOAL_PACE;
                    updateGoalPace(goalPace);
                    paceSum = 0.0;
                    paceAverage = 0.0;

                    distance = 0.0;
                    updateDistance(distance);

                    partFourFirstTick = false;
                }
                tickCounter++; // Track number of ticks on current part

                timeRemaining = l; // Store remaining time in the current part

                // Tracks the elapsed time since last alert
                timeElapsed = System.currentTimeMillis() - timeStart;
                updateTime();

                // Tracks the total elapsed time of the workout part
                partTimeElapsed = System.currentTimeMillis() - partTimeStart;

                speed = speedCalculator.getCurrentSpeed();
                //updateSpeed(speed);

                double lastPace = currentPace;
                currentPace = 60.0 / speed;

                if (currentPace > 30.0) {
                    currentPace = lastPace;
                }

                // Average current pace to current average
                if (Double.compare(currentPace, Double.NaN) != 0) {
                    paceSum += currentPace;
                    if (Double.compare(paceSum, Double.NaN) == 0) {
                        paceSum = 0.0;
                    }
                    paceAverage = paceSum / tickCounter;
                }

                if (tickCounter % PACE_UPDATE_INTERVAL == 0) {
                    updateCurrentPace(currentPace);
                }

                distance = speedCalculator.getCurrentDistance();
                updateDistance(distance);

                if (tickCounter % 10 == 0) {// calls pace alert every 10 seconds
                    paceAlert();
                }
            }

            @Override
            public void onFinish() {
                partFiveBegin();
            }

        }.start();
    }

    /**
     * Method called when Speed Calculation Service is successfully bound
     */
    public void partFiveBegin() {

        backButton.setEnabled(true); // Enable back button when fifth part begins
        nextButton.setEnabled(true); // Enable next button when fifth part begins

        speedCalculator.resetDistance();

        if (partFiveFirstRun) {
            timeRemaining = PART_FIVE_DURATION;
            tickCounter = 0;
            announcePace(PART_FIVE_GOAL_PACE);
            partFiveFirstRun = false;
        }

        speedCalculator.resetValues();
        currentPart = 5;

        // Update titles
        secondaryTitle.setText(PART_FIVE_SECONDARY_TITLE);

        final RadioButton partButton5 = (RadioButton) findViewById(R.id.radioButton5);
        partButton5.setChecked(true);

        partFiveFirstTick = true;

        partTimer = new CountDownTimer(timeRemaining, 1000) {
            @Override
            public void onTick(long l) {
                if (partFiveFirstTick) {
                    partTimeStart = System.currentTimeMillis();

                    updateTime();

                    goalPace = PART_FIVE_GOAL_PACE;
                    updateGoalPace(goalPace);
                    paceSum = 0.0;
                    paceAverage = 0.0;

                    distance = 0.0;
                    updateDistance(distance);

                    partFiveFirstTick = false;
                }
                tickCounter++; // Track number of ticks on current part

                timeRemaining = l; // Store remaining time in the current part

                // Tracks the elapsed time since last alert
                timeElapsed = System.currentTimeMillis() - timeStart;
                updateTime();

                // Tracks the total elapsed time of the workout part
                partTimeElapsed = System.currentTimeMillis() - partTimeStart;

                speed = speedCalculator.getCurrentSpeed();
                //updateSpeed(speed);

                double lastPace = currentPace;
                currentPace = 60.0 / speed;

                if (currentPace > 30.0) {
                    currentPace = lastPace;
                }

                // Average current pace to current average
                if (Double.compare(currentPace, Double.NaN) != 0) {
                    paceSum += currentPace;
                    if (Double.compare(paceSum, Double.NaN) == 0) {
                        paceSum = 0.0;
                    }
                    paceAverage = paceSum / tickCounter;
                }

                if (tickCounter % PACE_UPDATE_INTERVAL == 0) {
                    updateCurrentPace(currentPace);
                }

                distance = speedCalculator.getCurrentDistance();
                updateDistance(distance);

                if (tickCounter % 10 == 0) {// calls pace alert every 10 seconds
                    paceAlert();
                }
            }

            @Override
            public void onFinish() {
                partSixBegin();
            }

        }.start();
    }

    /**
     * Method called when Speed Calculation Service is successfully bound
     */
    public void partSixBegin() {

        backButton.setEnabled(true); // Enable back button when sixth part begins
        nextButton.setEnabled(false); // Enable next button when sixth part begins

        speedCalculator.resetDistance();

        if (partSixFirstRun) {
            timeRemaining = PART_SIX_DURATION;
            tickCounter = 0;
            announcePace(PART_SIX_GOAL_PACE);
            partSixFirstRun = false;
        }

        speedCalculator.resetValues();
        currentPart = 6;

        // Update titles
        secondaryTitle.setText(PART_SIX_SECONDARY_TITLE);

        final RadioButton partButton6 = (RadioButton) findViewById(R.id.radioButton6);
        partButton6.setChecked(true);

        partSixFirstTick = true;

        partTimer = new CountDownTimer(timeRemaining, 1000) {
            @Override
            public void onTick(long l) {
                if (partSixFirstTick) {
                    partTimeStart = System.currentTimeMillis();

                    updateTime();

                    goalPace = PART_SIX_GOAL_PACE;
                    updateGoalPace(goalPace);
                    paceSum = 0.0;
                    paceAverage = 0.0;

                    distance = 0.0;
                    updateDistance(distance);

                    partSixFirstTick = false;
                }
                tickCounter++; // Track number of ticks on current part

                timeRemaining = l; // Store remaining time in the current part

                // Tracks the elapsed time since last alert
                timeElapsed = System.currentTimeMillis() - timeStart;
                updateTime();

                // Tracks the total elapsed time of the workout part
                partTimeElapsed = System.currentTimeMillis() - partTimeStart;

                speed = speedCalculator.getCurrentSpeed();
                //updateSpeed(speed);

                double lastPace = currentPace;
                currentPace = 60.0 / speed;

                if (currentPace > 30.0) {
                    currentPace = lastPace;
                }

                // Average current pace to current average
                if (Double.compare(currentPace, Double.NaN) != 0) {
                    paceSum += currentPace;
                    if (Double.compare(paceSum, Double.NaN) == 0) {
                        paceSum = 0.0;
                    }
                    paceAverage = paceSum / tickCounter;
                }

                if (tickCounter % PACE_UPDATE_INTERVAL == 0) {
                    updateCurrentPace(currentPace);
                }

                distance = speedCalculator.getCurrentDistance();
                updateDistance(distance);

                if (tickCounter % 10 == 0) {// calls pace alert every 10 seconds
                    paceAlert();
                }
            }

            @Override
            public void onFinish() {
                pauseButton.setEnabled(false);
                updatePaceColor("#3498db");
                updatePaceText("Workout Finished!");
            }

        }.start();
    }

}
