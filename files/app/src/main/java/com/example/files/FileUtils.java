package com.example.files;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static List<String> readWordsFromAssets(Context context, String fileName) throws IOException {
        List<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(context.getAssets().open(fileName)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    words.add(line.trim());
                }
            }
        }
        return words;
    }

    public static File saveToDownloads(Context context, List<String> lines) {
        File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(downloadsDir, "combined_words.txt");

        try (FileOutputStream fos = new FileOutputStream(file);
             OutputStreamWriter osw = new OutputStreamWriter(fos);
             BufferedWriter writer = new BufferedWriter(osw)) {

            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }
}