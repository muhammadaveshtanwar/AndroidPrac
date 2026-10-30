package com.example.alertdialogdemo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView statusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusText = findViewById(R.id.statusText);

        // 1) Simple Alert Dialog with Yes / No / Later buttons
        Button alertBtn = findViewById(R.id.alertBtn);
        alertBtn.setOnClickListener(v -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle(R.string.dialog_title)
                    .setMessage(R.string.dialog_message)
                    .setPositiveButton(R.string.dialog_yes, (dialog, which) -> {
                        statusText.setText(R.string.status_yes);
                        Toast.makeText(MainActivity.this, R.string.toast_yes, Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton(R.string.dialog_no, (dialog, which) -> {
                        statusText.setText(R.string.status_no);
                    })
                    .setNeutralButton(R.string.dialog_later, (dialog, which) -> {
                        statusText.setText(R.string.status_later);
                    })
                    .show();
        });

        // 2) List AlertDialog
        Button listBtn = findViewById(R.id.listBtn);
        listBtn.setOnClickListener(v -> {

            final String[] subjects = getResources().getStringArray(R.array.subjects);

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle(R.string.list_title)
                    .setItems(subjects, (dialog, which) -> {
                        statusText.setText(getString(R.string.status_subject, subjects[which]));
                    })
                    .show();
        });

        // 3) Alert Dialog with an input field
        Button inputBtn = findViewById(R.id.inputBtn);
        inputBtn.setOnClickListener(v -> {

            EditText inputField = new EditText(MainActivity.this);
            inputField.setHint(R.string.input_hint);

            LinearLayout container = new LinearLayout(MainActivity.this);
            int padding = (int) (16 * getResources().getDisplayMetrics().density);
            container.setPadding(padding, 0, padding, 0);
            container.addView(inputField);

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle(R.string.input_title)
                    .setView(container)
                    .setPositiveButton(R.string.dialog_save, (dialog, which) -> {
                        String name = inputField.getText().toString().trim();
                        if (name.isEmpty()) {
                            statusText.setText(R.string.status_empty);
                        } else {
                            statusText.setText(getString(R.string.status_saved, name));
                        }
                    })
                    .setNegativeButton(R.string.dialog_cancel, null)
                    .show();
        });
    }
}
