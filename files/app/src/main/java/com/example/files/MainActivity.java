package com.example.files;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Thread(() -> {
            try {
                List<String> words = FileUtils.readWordsFromAssets(this, "words.txt");
                List<String> combined = WordCombiner.combineWords(words);
                File file = FileUtils.saveToDownloads(this, combined);

                runOnUiThread(() ->
                        Toast.makeText(this, "Saved to: " + file.getAbsolutePath(), Toast.LENGTH_LONG).show()
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}