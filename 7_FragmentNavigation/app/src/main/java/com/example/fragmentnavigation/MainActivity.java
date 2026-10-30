package com.example.fragmentnavigation;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Show HomeFragment when the app starts
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new HomeFragment())
                    .commit();
        }

        // Bottom buttons navigate between the three fragments
        Button homeBtn = findViewById(R.id.homeBtn);
        Button profileBtn = findViewById(R.id.profileBtn);
        Button aboutBtn = findViewById(R.id.aboutBtn);

        homeBtn.setOnClickListener(v -> showFragment(new HomeFragment()));
        profileBtn.setOnClickListener(v -> showFragment(new ProfileFragment()));
        aboutBtn.setOnClickListener(v -> showFragment(new AboutFragment()));
    }

    // Replaces the current fragment with the new one and keeps history
    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)   // back button se peeche ja sakte ho
                .commit();
    }
}
