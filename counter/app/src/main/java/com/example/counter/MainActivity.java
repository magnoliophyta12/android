package com.example.counter;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView launchCountView, totalTimeView;
    private Handler handler;
    private Runnable updateRunnable;
    private LaunchCounter counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        launchCountView = findViewById(R.id.launchCountView);
        totalTimeView = findViewById(R.id.totalTimeView);
        counter = (LaunchCounter) getApplication();

        updateUI();

        handler = new Handler();
        updateRunnable = new Runnable() {
            @Override
            public void run() {
                updateUI();
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(updateRunnable);
    }

    private void updateUI() {
        launchCountView.setText("Запусків: " + counter.getLaunchCount());
        totalTimeView.setText("Час у додатку: " + counter.getTotalTime() + " сек.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(updateRunnable);
    }
}