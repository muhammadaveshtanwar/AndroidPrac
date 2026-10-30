package com.example.programmingresources;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // String resource used in Java code
        TextView titleText = findViewById(R.id.titleText);
        titleText.setText(R.string.welcome_title);

        Button readMoreBtn = findViewById(R.id.readMoreBtn);
        readMoreBtn.setOnClickListener(v -> {
            // Color resource used in Java code
            int color = getColor(R.color.accent);
            readMoreBtn.setTextColor(color);

            // String resource with formatting used in Java code
            Toast.makeText(this, getString(R.string.toast_message), Toast.LENGTH_SHORT).show();
        });
    }
}
