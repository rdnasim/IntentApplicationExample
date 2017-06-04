package com.example.rdnasim.intentapplicationexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

import static java.lang.System.currentTimeMillis;

public class MyService extends Service {
    private static final String TAG="com.example.rdnasim.intentapplicationexample";
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"The intent is onStartCommand");

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<5;i++){
                    long Futuretime = System.currentTimeMillis()+5000;
                    while (currentTimeMillis()<Futuretime){
                        synchronized (this){
                            try {
                                wait(Futuretime-System.currentTimeMillis());
                                Log.i(TAG,"The service is running");
                            }catch (Exception e){

                            }
                        }
                    }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        return Service.START_STICKY;
    }
    @Override
    public void onDestroy() {
        Log.i(TAG,"The intent is onDestroy");

    }

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
