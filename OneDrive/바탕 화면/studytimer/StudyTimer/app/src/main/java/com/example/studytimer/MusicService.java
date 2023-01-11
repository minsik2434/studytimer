package com.example.studytimer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.security.Provider;

public class MusicService extends Service {

    MediaPlayer mp;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        android.util.Log.i("음악서비스","onStartCommand()");
        mp = MediaPlayer.create(this,R.raw.rain);
        mp.setLooping(true);
        mp.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        android.util.Log.i("음악서비스","onDestroy()");
        mp.stop();
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        android.util.Log.i("음악서비스","onCreate()");
        super.onCreate();
    }
}
