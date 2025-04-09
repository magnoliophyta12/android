package com.example.diagram;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BarChart barChart = findViewById(R.id.barChart);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 39.5f));
        entries.add(new BarEntry(1f, 39.8f));
        entries.add(new BarEntry(2f, 40.1f));
        entries.add(new BarEntry(3f, 39.9f));
        entries.add(new BarEntry(4f, 40.3f));

        String[] dates = new String[] {
                "01.04", "02.04", "03.04", "04.04", "05.04"
        };

        BarDataSet dataSet = new BarDataSet(entries, "USD to UAH");
        dataSet.setColor(Color.parseColor("#008577"));
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(12f);
        dataSet.setValueFormatter(new DollarValueFormatter());

        BarData barData = new BarData(dataSet);
        barChart.setData(barData);

        Description description = new Description();
        description.setText("Динаміка курсу долара");
        description.setTextSize(12f);
        barChart.setDescription(description);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(dates));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setDrawGridLines(false);
    }

    private static class DollarValueFormatter extends ValueFormatter {
        @Override
        public String getFormattedValue(float value) {
            return String.format(Locale.US, "%.2f", value);
        }
    }
}
