package com.example.graphics;

import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.view.Display;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyDrawer d = new MyDrawer(this);
        setContentView(d);
    }
}