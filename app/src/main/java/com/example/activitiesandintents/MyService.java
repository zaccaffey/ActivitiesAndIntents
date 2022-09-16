package com.example.activitiesandintents;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private String TAG = "TAG";
    private IBinder binder = new LocalBinder();

    // Return the communication channel to the service. May return null if clients can not bind to the service
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "Service Created");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        // indicate in the log that the service has started
        Log.i(TAG, "Service Started --- " + intent.getStringExtra("filename"));
        /*MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.music_file);
        mediaPlayer.start();*/
        return START_STICKY;
    }

    // service function that will be accessible when we bind the activity to the service
    public int add(int fno, int sno) {
        return fno + sno;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Service Destroyed");
    }

    public class LocalBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }
}