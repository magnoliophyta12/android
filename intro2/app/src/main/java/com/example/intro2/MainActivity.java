package com.example.intro2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private static final int NEXT_CLASS_DAYS = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long currentTime = System.currentTimeMillis();

                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DAY_OF_YEAR, NEXT_CLASS_DAYS);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);

                long nextClassTime = calendar.getTimeInMillis();
                long timeLeft = nextClassTime - currentTime;

                long days = TimeUnit.MILLISECONDS.toDays(timeLeft);
                long hours = TimeUnit.MILLISECONDS.toHours(timeLeft) % 24;
                long minutes = TimeUnit.MILLISECONDS.toMinutes(timeLeft) % 60;
                long seconds = TimeUnit.MILLISECONDS.toSeconds(timeLeft) % 60;

                String timeLeftText = days + " дні, " + hours + " годин, " + minutes + " хвилин, " + seconds + " секунд";
                Toast.makeText(MainActivity.this, timeLeftText, Toast.LENGTH_LONG).show();
            }
        });
    }
}