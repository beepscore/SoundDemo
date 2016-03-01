package com.beepscore.android.sounddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private AudioPlayer mPlayer = new AudioPlayer();
    private Button mPauseButton;
    private Button mPlayButton;
    private Button mStopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View contentView = this.findViewById(android.R.id.content);

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
