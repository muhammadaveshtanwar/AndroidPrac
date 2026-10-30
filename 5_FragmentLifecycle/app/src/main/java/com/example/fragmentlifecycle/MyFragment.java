package com.example.fragmentlifecycle;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyFragment extends Fragment {

    // Tag used to filter these lines in Logcat
    private static final String TAG = "FragmentLifecycle";

    TextView statusText, countText;
    int eventCount = 0;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: fragment is attached to activity");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: fragment is being created");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: fragment view is being created");
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        statusText = view.findViewById(R.id.fragStatusText);
        countText = view.findViewById(R.id.fragCountText);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: fragment view is created");
        showEvent(getString(R.string.event_onviewcreated));
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: fragment becomes visible");
        showEvent(getString(R.string.event_onstart));
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: fragment is active");
        showEvent(getString(R.string.event_onresume));
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: fragment loses focus");
        showEvent(getString(R.string.event_onpause));
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: fragment is no longer visible");
        showEvent(getString(R.string.event_onstop));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: fragment view is destroyed");
        // view is gone, so stop using statusText after this
        statusText = null;
        countText = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: fragment is being destroyed");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: fragment is detached from activity");
    }

    // Updates the fragment screen with the last lifecycle method that ran
    private void showEvent(String event) {
        if (statusText != null) {
            eventCount++;
            statusText.setText(event);
            countText.setText(getString(R.string.count_text, eventCount));
        }
    }
}
