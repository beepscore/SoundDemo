package com.beepscore.android.sounddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // If rotate device while playing,
        // activity restarts and phone plays 2 overlapping audios
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.one_small_step_trim);
        mediaPlayer.start();
    }
}
