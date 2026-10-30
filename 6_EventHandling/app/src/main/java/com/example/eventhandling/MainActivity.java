package com.example.eventhandling;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView statusText, countText;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusText = findViewById(R.id.statusText);
        countText = findViewById(R.id.countText);

        // ---- Way 1: setOnClickListener with anonymous class (Count button) ----
        Button countBtn = findViewById(R.id.countBtn);
        countBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                countText.setText(getString(R.string.count_text, count));
                statusText.setText(R.string.status_count);
            }
        });

        // ---- Way 2: setOnClickListener with lambda (Toast button) ----
        Button toastBtn = findViewById(R.id.toastBtn);
        toastBtn.setOnClickListener(v -> {
            Toast.makeText(this, R.string.toast_clicked, Toast.LENGTH_SHORT).show();
            statusText.setText(R.string.status_toast);
        });

        // ---- Way 3: one listener on many views + switch on view id (Colour buttons) ----
        Button redBtn = findViewById(R.id.redBtn);
        Button greenBtn = findViewById(R.id.greenBtn);
        View.OnClickListener colourListener = v -> {
            int id = v.getId();
            if (id == R.id.redBtn) {
                statusText.setTextColor(getColor(R.color.sample_red));
                statusText.setText(R.string.status_red);
            } else if (id == R.id.greenBtn) {
                statusText.setTextColor(getColor(R.color.sample_green));
                statusText.setText(R.string.status_green);
            }
        };
        redBtn.setOnClickListener(colourListener);
        greenBtn.setOnClickListener(colourListener);

        // ---- Way 4: android:onClick attribute in XML (handled by onXmlClick below) ----
    }

    // Called directly from activity_main.xml: android:onClick="onXmlClick"
    public void onXmlClick(View view) {
        statusText.setText(R.string.status_xml);
        statusText.setTextColor(getColor(R.color.primary));
        Toast.makeText(this, R.string.toast_xml, Toast.LENGTH_SHORT).show();
    }
}
