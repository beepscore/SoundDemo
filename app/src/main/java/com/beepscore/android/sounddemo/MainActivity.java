package com.beepscore.android.sounddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private AudioPlayer mPlayer;
    private Button mPauseButton;
    private Button mPlayButton;
    private Button mStopButton;
    private final String LOG_TAG = MainActivity.class.getSimpleName();
    private SeekBar mTimeSeekBar;
    private SeekBar mVolumeSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPlayer = new AudioPlayer(this);

        setContentView(R.layout.activity_main);
        View contentView = this.findViewById(android.R.id.content);

        configureVolumeSeekBar(contentView);

        configureTimeSeekBar(contentView);

        configureButtons(contentView);
    }

    private void configureVolumeSeekBar(View contentView) {
        mVolumeSeekBar = (SeekBar)contentView.findViewById(R.id.volumeSeekBar);
        mVolumeSeekBar.setMax(mPlayer.mVolumeMax);
        mVolumeSeekBar.setProgress(mPlayer.mVolumeCurrent);

        mVolumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i(LOG_TAG, "volume " + Integer.toString(progress));
                mPlayer.setStreamVolume(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.i(LOG_TAG, "volume onStartTrackingTouch");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.i(LOG_TAG, "volume onStopTrackingTouch");
            }
        });
    }

    private void configureTimeSeekBar(View contentView) {
        mTimeSeekBar = (SeekBar)contentView.findViewById(R.id.timeSeekBar);
        mTimeSeekBar.setMax(mPlayer.getDuration());
        Log.i(LOG_TAG, "duration " + Integer.toString(mPlayer.getDuration()));

        configureProgressTimer();

        mTimeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i(LOG_TAG, "time " + Integer.toString(progress));
                if (fromUser) {
                    Log.i(LOG_TAG, "fromUser time " + Integer.toString(progress));
                } else {
                    Log.i(LOG_TAG, "time " + Integer.toString(progress));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.i(LOG_TAG, "time onStartTrackingTouch");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.i(LOG_TAG, "time onStopTrackingTouch");
                mPlayer.seekTo(mTimeSeekBar.getProgress());
            }
        });
    }

    private void configureProgressTimer() {
        final int delayMsec = 0;
        final int periodMsec = 100;
        new Timer().scheduleAtFixedRate(new TimerTask() {
            // runnable
            @Override
            public void run() {
                mTimeSeekBar.setProgress(mPlayer.getCurrentPosition());
            }
        }, delayMsec, periodMsec);
    }

    private void configureButtons(View contentView) {
        mPauseButton = (Button)contentView.findViewById(R.id.pauseButton);
        mPauseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (mPlayer != null) {
                    mPlayer.pause();
                }
            }
        });

        mPlayButton = (Button)contentView.findViewById(R.id.playButton);
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (mPlayer != null) {
                    mPlayer.seekTo(mTimeSeekBar.getProgress());
                    mPlayer.play(getApplicationContext());
                }
            }
        });

        mStopButton = (Button)contentView.findViewById(R.id.stopButton);
        mStopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (mPlayer != null) {
                    mPlayer.stop();
                }
            }
        });
    }
}
