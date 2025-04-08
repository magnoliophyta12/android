package com.example.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationManagerCompat;

public class ActionReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action == null) return;

        switch (action) {
            case "ACTION_PREV":
                Toast.makeText(context, "Previous", Toast.LENGTH_SHORT).show();
                break;
            case "ACTION_PLAY":
                Toast.makeText(context, "Play/Pause", Toast.LENGTH_SHORT).show();
                break;
            case "ACTION_NEXT":
                Toast.makeText(context, "Next", Toast.LENGTH_SHORT).show();
                break;
            case "ACTION_CLOSE":
                NotificationManagerCompat.from(context).cancel(1234);
                break;
        }
    }
}