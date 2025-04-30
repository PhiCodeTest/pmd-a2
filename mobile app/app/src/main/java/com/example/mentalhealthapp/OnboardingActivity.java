package com.example.mentalhealthapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

public class OnboardingActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private OnboardingAdapter adapter;
    private LinearLayout dotsIndicator;
    private View[] dots;
    private Button continueButton;
    private TextView skipButton;
    private Button getStartedButton;
    private LinearLayout normalNavButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        // Initialize views
        viewPager = findViewById(R.id.viewPager);
        dotsIndicator = findViewById(R.id.dotsIndicator);
        continueButton = findViewById(R.id.continueButton);
        skipButton = findViewById(R.id.skipButton);
        getStartedButton = findViewById(R.id.getStartedButton);
        normalNavButtons = findViewById(R.id.normalNavButtons);

        // Set up adapter
        adapter = new OnboardingAdapter(this);
        viewPager.setAdapter(adapter);
        
        // Set up dot indicators
        setupDotIndicators();

        // Handle navigation buttons
        skipButton.setOnClickListener(v -> navigateToSignupActivity());

        continueButton.setOnClickListener(v -> {
            if (viewPager.getCurrentItem() < adapter.getItemCount() - 1) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        });

        getStartedButton.setOnClickListener(v -> navigateToSignupActivity());

        // Add page change listener
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                updateDotIndicators(position);
                updateButtonVisibility(position);
            }
        });
    }

    private void setupDotIndicators() {
        dots = new View[adapter.getItemCount()];
        
        // Use already created dots from XML
        dots[0] = findViewById(R.id.dot1);
        dots[1] = findViewById(R.id.dot2);
        dots[2] = findViewById(R.id.dot3);
    }

    private void updateDotIndicators(int position) {
        for (int i = 0; i < dots.length; i++) {
            if (i == position) {
                dots[i].setBackground(getDrawable(R.drawable.tab_indicator_selected));
            } else {
                dots[i].setBackground(getDrawable(R.drawable.tab_indicator_default));
            }
        }
    }

    private void updateButtonVisibility(int position) {
        if (position == adapter.getItemCount() - 1) {
            // On last page
            normalNavButtons.setVisibility(View.GONE);
            getStartedButton.setVisibility(View.VISIBLE);
        } else {
            // Not on last page
            normalNavButtons.setVisibility(View.VISIBLE);
            getStartedButton.setVisibility(View.GONE);
        }
    }

    private void navigateToSignupActivity() {
        Intent intent = new Intent(OnboardingActivity.this, SignupActivity.class);
        startActivity(intent);
    }
} 