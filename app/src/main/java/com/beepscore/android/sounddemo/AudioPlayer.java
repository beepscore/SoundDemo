package com.beepscore.android.sounddemo;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

/**
 * Created by stevebaker on 2/29/16.
 * https://developer.android.com/reference/android/media/MediaPlayer.html
 */
public class AudioPlayer {
    private AudioManager mAudioManager;
    private MediaPlayer mPlayer;
    int mVolumeMax;
    int mVolumeCurrent;

    public AudioPlayer(Context context) {
        mAudioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        mVolumeMax = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        mVolumeCurrent = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
    }

    public void stop() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }

    public void play(Context context) {

        // prevent creation of multiple media players.
        if (mPlayer == null) {
            mPlayer = MediaPlayer.create(context, R.raw.one_small_step_trim);
        }

        // as soon as playback is done, call stop to release media player
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                stop();
            }
        });

        mPlayer.start();
    }

    public void pause() {
        if (mPlayer != null) {
            mPlayer.pause();
        }
    }

    public void setStreamVolume(int volume) {
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
    }
}