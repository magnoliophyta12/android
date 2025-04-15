package com.example.firebase;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.*;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button btnSend, btnReceive;
    private TextView textView;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        btnSend = findViewById(R.id.btnSend);
        btnReceive = findViewById(R.id.btnReceive);
        textView = findViewById(R.id.textView);

        database = FirebaseDatabase.getInstance().getReference("messages");

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString().trim();
                if (!text.isEmpty()) {
                    String key = database.push().getKey();
                    if (key != null) {
                        database.child(key).setValue(text);
                        editText.setText("");
                        Toast.makeText(MainActivity.this, "Дані надіслано!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        StringBuilder result = new StringBuilder();
                        for (DataSnapshot child : snapshot.getChildren()) {
                            result.append(child.getValue(String.class)).append("\n");
                        }
                        textView.setText(result.toString());
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        Toast.makeText(MainActivity.this, "Помилка: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}