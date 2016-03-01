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

    int mTimeCurrent;

    public AudioPlayer(Context context) {
        mAudioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        mVolumeMax = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        mVolumeCurrent = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        mPlayer = MediaPlayer.create(context, R.raw.one_small_step_trim);
    }

    public void setStreamVolume(int volume) {
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
    }

    public int getDuration() {
        if (mPlayer != null) {
            return mPlayer.getDuration();
        } else {
            return 0;
        }
    }

    public void play(Context context) {

        if (mPlayer == null) {
            mPlayer = MediaPlayer.create(context, R.raw.one_small_step_trim);

            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    stop();
                }
            });
        }

        mTimeCurrent = mPlayer.getCurrentPosition();
        mPlayer.start();
    }

    public void pause() {
        if (mPlayer != null) {
            mPlayer.pause();
        }
    }

    public void seekTo(int msec) {
        if (mPlayer != null) {
            mPlayer.seekTo(msec);
        }
    }

    public void stop() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }

}