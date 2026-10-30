package com.example.activitylifecycle;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Tag used to filter these lines in Logcat
    private static final String TAG = "LifecycleDemo";

    TextView statusText, countText;
    int eventCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: activity is being created");
        statusText = findViewById(R.id.statusText);
        countText = findViewById(R.id.countText);
        showEvent(getString(R.string.event_oncreate));

        Button finishBtn = findViewById(R.id.finishBtn);
        finishBtn.setOnClickListener(v -> finish()); // triggers onPause -> onStop -> onDestroy
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: activity becomes visible");
        showEvent(getString(R.string.event_onstart));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: activity is in foreground and interactive");
        showEvent(getString(R.string.event_onresume));
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: activity loses focus (partial visibility)");
        showEvent(getString(R.string.event_onpause));
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: activity is no longer visible");
        showEvent(getString(R.string.event_onstop));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: activity is restarting after onStop");
        showEvent(getString(R.string.event_onrestart));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: activity is being destroyed");
        // view is going away, so only Log here
    }

    // Updates the screen with the last lifecycle method that ran
    private void showEvent(String event) {
        eventCount++;
        statusText.setText(event);
        countText.setText(getString(R.string.count_text, eventCount));
    }
}
