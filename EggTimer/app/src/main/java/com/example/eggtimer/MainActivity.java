package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    TextView timerTextView ;
    SeekBar timerSeekbar ;
    Boolean counterIsActive = false ;
    Button goButton ;
    CountDownTimer countDownTimer ;

    public void resetTimer(){

        timerTextView.setText("00:30");
        timerSeekbar.setProgress(30);
        timerSeekbar.setEnabled(true);
        countDownTimer.cancel();
        goButton.setText("GO");
        counterIsActive = false ;
    }

    public void buttonOnClick(View view) {

        if(counterIsActive){

            resetTimer();

        } else {

            counterIsActive = true;
            timerSeekbar.setEnabled(false);
            goButton.setText("STOP!");


             countDownTimer = new CountDownTimer(timerSeekbar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                    updateTimer((int) millisUntilFinished / 1000);

                }

                @Override
                public void onFinish() {

                    MediaPlayer mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mPlayer.start();
                    resetTimer();

                }
            }.start();
        }
    }

        public void updateTimer (int secondsLeft){

            int minuites = secondsLeft / 60 ;
            int seconds = secondsLeft - (minuites * 60) ;

            String secondsString = Integer.toString(seconds);
            String minuitesString = Integer.toString(minuites);

            if(minuites <= 9 ){
                minuitesString = "0" + Integer.toString(minuites);
            }

            if(seconds <= 9 ){
                secondsString = "0" + Integer.toString(seconds);
            }

            timerTextView.setText(minuitesString + ":" + secondsString);

        }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         timerSeekbar = findViewById(R.id.timerSeekBar);
         timerTextView = findViewById(R.id.countdownTextView);
         goButton = findViewById(R.id.goButton);

        timerSeekbar.setMax(600);
        timerSeekbar.setProgress(30);

        timerSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateTimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
