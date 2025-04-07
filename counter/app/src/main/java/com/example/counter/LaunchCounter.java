package com.example.counter;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Handler;
public class LaunchCounter extends Application {

    private static final String PREFS_NAME = "AppStats";
    private static final String KEY_LAUNCH_COUNT = "LaunchCount";
    private static final String KEY_TOTAL_TIME = "TotalTime";

    private int launchCount;
    private long totalTime;
    private long startTime;
    private SharedPreferences prefs;
    private Handler handler;
    private Runnable timeUpdater;

    @Override
    public void onCreate() {
        super.onCreate();

        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        launchCount = prefs.getInt(KEY_LAUNCH_COUNT, 0);
        totalTime = prefs.getLong(KEY_TOTAL_TIME, 0);

        launchCount++;
        prefs.edit().putInt(KEY_LAUNCH_COUNT, launchCount).apply();

        startTime = System.currentTimeMillis();
        handler = new Handler();
        timeUpdater = new Runnable() {
            @Override
            public void run() {
                long elapsed = (System.currentTimeMillis() - startTime) / 1000;
                prefs.edit().putLong(KEY_TOTAL_TIME, totalTime + elapsed).apply();
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(timeUpdater);
    }

    public int getLaunchCount() {
        return launchCount;
    }

    public long getTotalTime() {
        return prefs.getLong(KEY_TOTAL_TIME, 0);
    }
}