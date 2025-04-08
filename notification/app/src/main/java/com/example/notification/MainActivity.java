package com.example.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.POST_NOTIFICATIONS},
                        1000);
            } else {
                showMediaNotification(this);
            }
        } else {
            showMediaNotification(this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            showMediaNotification(this);
        } else {
            Toast.makeText(this, "Сповіщення не дозволені", Toast.LENGTH_SHORT).show();
        }
    }
    private void showMediaNotification(Context context) {
        String channelId = "channel_id";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelName = "Media Controls";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }

        RemoteViews customView = new RemoteViews(getPackageName(), R.layout.notification);

        Intent prevIntent = new Intent(this, ActionReceiver.class).setAction("ACTION_PREV");
        PendingIntent prevPending = PendingIntent.getBroadcast(this, 0, prevIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        customView.setOnClickPendingIntent(R.id.btn_prev, prevPending);

        Intent playIntent = new Intent(this, ActionReceiver.class).setAction("ACTION_PLAY");
        PendingIntent playPending = PendingIntent.getBroadcast(this, 1, playIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        customView.setOnClickPendingIntent(R.id.btn_play, playPending);

        Intent nextIntent = new Intent(this, ActionReceiver.class).setAction("ACTION_NEXT");
        PendingIntent nextPending = PendingIntent.getBroadcast(this, 2, nextIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        customView.setOnClickPendingIntent(R.id.btn_next, nextPending);

        Intent closeIntent = new Intent(this, ActionReceiver.class).setAction("ACTION_CLOSE");
        PendingIntent closePending = PendingIntent.getBroadcast(this, 3, closeIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        customView.setOnClickPendingIntent(R.id.btn_close, closePending);

        Notification notification = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                .setCustomContentView(customView)
                .setAutoCancel(false)
                .setOngoing(true)
                .build();

        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS)
                == PackageManager.PERMISSION_GRANTED) {
            NotificationManagerCompat.from(context).notify(1001, notification);
        } else {
            Log.w("Notify", "Notification permission not granted");
        }
    }


}

