package com.example.optionsmenu;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView statusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusText = findViewById(R.id.statusText);
    }

    // Inflates the options menu from res/menu/main_menu.xml
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // Handles the click on each menu item
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_refresh) {
            statusText.setText(R.string.status_refreshed);
            Toast.makeText(this, R.string.toast_refresh, Toast.LENGTH_SHORT).show();
            return true;

        } else if (id == R.id.menu_search) {
            statusText.setText(R.string.status_search);
            Toast.makeText(this, R.string.toast_search, Toast.LENGTH_SHORT).show();
            return true;

        } else if (id == R.id.menu_settings) {
            statusText.setText(R.string.status_settings);
            Toast.makeText(this, R.string.toast_settings, Toast.LENGTH_SHORT).show();
            return true;

        } else if (id == R.id.menu_about) {
            statusText.setText(R.string.status_about);
            Toast.makeText(this, R.string.toast_about, Toast.LENGTH_SHORT).show();
            return true;

        } else if (id == R.id.menu_exit) {
            finish(); // closes the app
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
