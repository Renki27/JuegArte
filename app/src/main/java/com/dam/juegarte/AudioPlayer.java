package com.dam.juegarte;

import android.content.Context;
import android.media.MediaPlayer;

public class AudioPlayer {
    MediaPlayer mPlayer;
    private static AudioPlayer audioPlayer;
    private Context context;

    public AudioPlayer(Context context) {
        this.context = context;
    }

    public static AudioPlayer getInstance(Context context) {
        if(audioPlayer == null) {
            audioPlayer = new AudioPlayer(context);
        }
        return audioPlayer;
    }

    public void play(Context context) {
        if (mPlayer == null) {
            mPlayer = MediaPlayer.create(context, R.raw.song);
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }
        mPlayer.start();
    }
    public void pause() {
        if (mPlayer != null) {
            mPlayer.pause();
        }
    }
    public void  stop() {
        stopPlayer();
    }
    private void stopPlayer(){
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }
}
