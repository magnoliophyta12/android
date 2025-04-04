package com.example.graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class MyDrawer extends View {
    private Paint paint;
    private Paint textPaint;
    private Path octagonPath;

    public MyDrawer(Context context) {
        super(context);
        init();
    }
    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(300);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setFakeBoldText(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        int size = Math.min(width, height) - 50;
        float centerX = width / 2f;
        float centerY = height / 2f;
        float radius = size / 2f;

        octagonPath = new Path();
        for (int i = 0; i < 8; i++) {
            double angle = Math.toRadians(22.5 + i * 45);
            float x = (float) (centerX + radius * Math.cos(angle));
            float y = (float) (centerY + radius * Math.sin(angle));
            if (i == 0) {
                octagonPath.moveTo(x, y);
            } else {
                octagonPath.lineTo(x, y);
            }
        }
        octagonPath.close();
        canvas.drawPath(octagonPath, paint);

        canvas.drawText("STOP", centerX, centerY+100, textPaint);
    }
}
