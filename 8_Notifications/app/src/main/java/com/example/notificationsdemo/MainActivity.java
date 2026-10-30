package com.example.notificationsdemo;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {

    // Channel is required for Android 8.0 (API 26) and above
    private static final String CHANNEL_ID = "practical_channel";
    private static final int SIMPLE_ID = 1;
    private static final int BIG_ID = 2;

    TextView statusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusText = findViewById(R.id.statusText);

        // Create the notification channel once
        createNotificationChannel();

        // 1) Simple notification
        Button simpleBtn = findViewById(R.id.simpleBtn);
        simpleBtn.setOnClickListener(v -> {
            showNotification(SIMPLE_ID,
                    getString(R.string.simple_title),
                    getString(R.string.simple_message));
            statusText.setText(R.string.status_simple);
        });

        // 2) Notification with big text style
        Button bigBtn = findViewById(R.id.bigBtn);
        bigBtn.setOnClickListener(v -> {
            showBigNotification();
            statusText.setText(R.string.status_big);
        });

        // 3) Cancel all notifications
        Button cancelBtn = findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(v -> {
            NotificationManager manager =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.cancelAll();   // removes every notification we posted
            statusText.setText(R.string.status_cancel);
            Toast.makeText(this, R.string.toast_cancel, Toast.LENGTH_SHORT).show();
        });
    }

    // Android 8.0+ requires a NotificationChannel before posting
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    getString(R.string.channel_name),
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(getString(R.string.channel_description));

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    // Tapping the notification opens this activity again
    private PendingIntent getPendingIntent() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
    }

    private void showNotification(int id, String title, String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(getPendingIntent())
                .setAutoCancel(true);   // notification hat jati hai tap par

        NotificationManager manager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(id, builder.build());
    }

    private void showBigNotification() {
        String longMessage = getString(R.string.big_message);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(getString(R.string.big_title))
                .setContentText(getString(R.string.big_preview))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(longMessage))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(getPendingIntent())
                .setAutoCancel(true);

        NotificationManager manager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(BIG_ID, builder.build());
    }
}
