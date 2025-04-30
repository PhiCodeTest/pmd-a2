package com.example.mentalhealthapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InsightActivity extends AppCompatActivity {

    private ImageButton moreButton;
    private ImageButton cameraButton;
    private ImageButton qrButton;
    private ImageButton cameraButton2;
    private ImageButton qrButton2;
    private ImageButton prevWeekButton;
    private ImageButton nextWeekButton;
    private TextView weeklyPeriod;
    private TextView monthlyPeriod;
    private TextView yearlyPeriod;
    private TextView dateRangeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insight);

        // Initialize views
        moreButton = findViewById(R.id.moreButton);
        cameraButton = findViewById(R.id.cameraButton);
        qrButton = findViewById(R.id.qrButton);
        cameraButton2 = findViewById(R.id.cameraButton2);
        qrButton2 = findViewById(R.id.qrButton2);
        prevWeekButton = findViewById(R.id.prevWeekButton);
        nextWeekButton = findViewById(R.id.nextWeekButton);
        weeklyPeriod = findViewById(R.id.weeklyPeriod);
        monthlyPeriod = findViewById(R.id.monthlyPeriod);
        yearlyPeriod = findViewById(R.id.yearlyPeriod);
        dateRangeText = findViewById(R.id.dateRangeText);

        // Set click listeners
        moreButton.setOnClickListener(v -> {
            Toast.makeText(this, "More options", Toast.LENGTH_SHORT).show();
        });
        
        cameraButton.setOnClickListener(v -> {
            Toast.makeText(this, "Growth area camera", Toast.LENGTH_SHORT).show();
        });
        
        qrButton.setOnClickListener(v -> {
            Toast.makeText(this, "Growth area QR code", Toast.LENGTH_SHORT).show();
        });
        
        cameraButton2.setOnClickListener(v -> {
            Toast.makeText(this, "Mood tracker camera", Toast.LENGTH_SHORT).show();
        });
        
        qrButton2.setOnClickListener(v -> {
            Toast.makeText(this, "Mood tracker QR code", Toast.LENGTH_SHORT).show();
        });
        
        // Period selection
        weeklyPeriod.setOnClickListener(v -> {
            setPeriodSelection(PeriodType.WEEKLY);
        });
        
        monthlyPeriod.setOnClickListener(v -> {
            setPeriodSelection(PeriodType.MONTHLY);
        });
        
        yearlyPeriod.setOnClickListener(v -> {
            setPeriodSelection(PeriodType.YEARLY);
        });
        
        // Date navigation
        prevWeekButton.setOnClickListener(v -> {
            Toast.makeText(this, "Previous week", Toast.LENGTH_SHORT).show();
        });
        
        nextWeekButton.setOnClickListener(v -> {
            Toast.makeText(this, "Next week", Toast.LENGTH_SHORT).show();
        });
    }
    
    private enum PeriodType {
        WEEKLY,
        MONTHLY,
        YEARLY
    }
    
    private void setPeriodSelection(PeriodType periodType) {
        // Reset all to unselected
        weeklyPeriod.setBackgroundResource(0);
        monthlyPeriod.setBackgroundResource(0);
        yearlyPeriod.setBackgroundResource(0);
        
        weeklyPeriod.setTextColor(getResources().getColor(android.R.color.darker_gray, getTheme()));
        monthlyPeriod.setTextColor(getResources().getColor(android.R.color.darker_gray, getTheme()));
        yearlyPeriod.setTextColor(getResources().getColor(android.R.color.darker_gray, getTheme()));
        
        // Set the selected one
        TextView selectedPeriod = null;
        String dateRange = "";
        
        switch (periodType) {
            case WEEKLY:
                selectedPeriod = weeklyPeriod;
                dateRange = "Dec 16 - Dec 22, 2024";
                break;
                
            case MONTHLY:
                selectedPeriod = monthlyPeriod;
                dateRange = "December 2024";
                break;
                
            case YEARLY:
                selectedPeriod = yearlyPeriod;
                dateRange = "Year 2024";
                break;
        }
        
        if (selectedPeriod != null) {
            selectedPeriod.setBackgroundResource(R.drawable.selected_period_bg);
            selectedPeriod.setTextColor(getResources().getColor(R.color.button_color, getTheme()));
            
            // Update date range
            dateRangeText.setText(dateRange);
        }
    }
} 