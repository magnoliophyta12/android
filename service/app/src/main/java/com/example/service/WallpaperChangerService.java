package com.example.service;

import android.app.Service;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;

import java.io.IOException;

public class WallpaperChangerService extends Service {

    private HandlerThread handlerThread;
    private Handler handler;
    private Runnable wallpaperChanger;
    private int currentIndex = 0;

    private int[] imageIds = {
            R.drawable.wallpaper1,
            R.drawable.wallpaper2,
            R.drawable.wallpaper3
    };

    @Override
    public void onCreate() {
        super.onCreate();
        handlerThread = new HandlerThread("WallpaperChangerThread");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper());

        wallpaperChanger = new Runnable() {
            @Override
            public void run() {
                changeWallpaper();
                handler.postDelayed(this, 30_000);
            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler.removeCallbacks(wallpaperChanger);
        handler.post(wallpaperChanger);

        return START_STICKY;
    }

    private void changeWallpaper() {
        try {
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imageIds[currentIndex]);
            wallpaperManager.setBitmap(bitmap);

            currentIndex = (currentIndex + 1) % imageIds.length;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (handler != null && wallpaperChanger != null) {
            handler.removeCallbacks(wallpaperChanger);
        }
        if (handlerThread != null) {
            handlerThread.quitSafely();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}