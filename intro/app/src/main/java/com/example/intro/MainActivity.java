package com.example.intro;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private int buttonWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        buttonWidth = button.getLayoutParams().width;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonWidth += 10;
                button.getLayoutParams().width = buttonWidth;
                button.requestLayout();
            }
        });
    }
}
