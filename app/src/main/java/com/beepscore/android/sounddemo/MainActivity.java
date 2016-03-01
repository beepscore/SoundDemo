package com.beepscore.android.sounddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private AudioPlayer mPlayer = new AudioPlayer();
    private Button mPauseButton;
    private Button mPlayButton;
    private Button mStopButton;
    private SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final String LOG_TAG = "MainActivity";
        setContentView(R.layout.activity_main);
        View contentView = this.findViewById(android.R.id.content);

        mSeekBar = (SeekBar)contentView.findViewById(R.id.seekBar);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i(LOG_TAG, "onProgressChanged" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.i(LOG_TAG, "onStartTrackingTouch");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.i(LOG_TAG, "onStopTrackingTouch");
            }
        });

        mPauseButton = (Button)contentView.findViewById(R.id.pauseButton);
        mPauseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mPlayer.pause();
            }
        });

        mPlayButton = (Button)contentView.findViewById(R.id.playButton);
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mPlayer.play(getApplicationContext());
            }
        });

        mStopButton = (Button)contentView.findViewById(R.id.stopButton);
        mStopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mPlayer.stop();
            }
        });
    }
}
