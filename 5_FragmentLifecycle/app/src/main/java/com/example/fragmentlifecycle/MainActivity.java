package com.example.fragmentlifecycle;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    boolean fragmentLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load the fragment into the FrameLayout container when activity starts
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, new MyFragment())
                    .commit();
            fragmentLoaded = true;
        } else {
            fragmentLoaded = true;
        }

        // Remove and re-add the fragment to see the full fragment lifecycle
        Button toggleBtn = findViewById(R.id.toggleBtn);
        toggleBtn.setOnClickListener(v -> {

            Fragment current = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);

            if (current != null) {
                // Removing shows: onPause -> onStop -> onDestroyView -> onDestroy -> onDetach
                getSupportFragmentManager().beginTransaction()
                        .remove(current)
                        .commit();
                toggleBtn.setText(R.string.btn_add);
                fragmentLoaded = false;
            } else {
                // Adding shows: onAttach -> onCreate -> onCreateView -> onStart -> onResume
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragmentContainer, new MyFragment())
                        .commit();
                toggleBtn.setText(R.string.btn_remove);
                fragmentLoaded = true;
            }
        });

        if (fragmentLoaded) {
            toggleBtn.setText(R.string.btn_remove);
        }
    }
}
