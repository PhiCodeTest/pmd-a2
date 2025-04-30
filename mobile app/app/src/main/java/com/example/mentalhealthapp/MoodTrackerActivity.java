package com.example.mentalhealthapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MoodTrackerActivity extends AppCompatActivity {

    private static final int REQUEST_ADD_NOTES = 100;

    private ImageButton closeButton;
    private ImageView selectedMoodImage;
    private TextView moodLabel;
    private Button submitButton;
    private SeekBar moodSeekBar;
    
    // Define mood types as constants
    private static final String VERY_BAD = "Very Bad";
    private static final String BAD = "Bad";
    private static final String NEUTRAL = "Neutral";
    private static final String GOOD = "Good";
    private static final String VERY_GOOD = "Very Good";
    
    // Current selected mood
    private String currentMood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_tracker);

        // Initialize views
        closeButton = findViewById(R.id.closeButton);
        selectedMoodImage = findViewById(R.id.selectedMoodImage);
        moodLabel = findViewById(R.id.moodLabel);
        submitButton = findViewById(R.id.submitButton);
        moodSeekBar = findViewById(R.id.moodSeekBar);
        
        // Configure SeekBar
        moodSeekBar.setMax(4); // 5 positions (0-4)
        
        // Get the mood data from the intent
        String moodType = getIntent().getStringExtra("MOOD_TYPE");
        currentMood = moodType; // Store current mood
        
        // Set initial SeekBar position based on mood
        setInitialSeekBarPosition(moodType);
        
        // Set the mood UI based on the mood type
        setupMoodByType(moodType);

        // Set click listeners
        closeButton.setOnClickListener(v -> {
            // Go back to main screen
            goBackToMain();
        });
        
        submitButton.setOnClickListener(v -> {
            // Instead of saving mood and finishing, go to the notes page
            navigateToNotesPage();
        });
        
        // Set SeekBar listener to update mood when user slides
        moodSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    // Convert progress to mood type
                    String newMoodType = getMoodTypeFromProgress(progress);
                    currentMood = newMoodType;
                    setupMoodByType(newMoodType);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Not needed
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Not needed
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Go back to main screen
        goBackToMain();
    }
    
    private void goBackToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    
    private void navigateToNotesPage() {
        Intent intent = new Intent(this, MoodNotesActivity.class);
        intent.putExtra("MOOD_TYPE", currentMood);
        startActivity(intent);
        finish(); // Finish this activity as we're moving forward in the flow
    }
    
    private void setInitialSeekBarPosition(String moodType) {
        int position;
        switch (moodType) {
            case VERY_BAD:
                position = 0;
                break;
            case BAD:
                position = 1;
                break;
            case NEUTRAL:
                position = 2;
                break;
            case GOOD:
                position = 3;
                break;
            case VERY_GOOD:
                position = 4;
                break;
            default:
                position = 0;
                break;
        }
        moodSeekBar.setProgress(position);
    }
    
    private String getMoodTypeFromProgress(int progress) {
        switch (progress) {
            case 0:
                return VERY_BAD;
            case 1:
                return BAD;
            case 2:
                return NEUTRAL;
            case 3:
                return GOOD;
            case 4:
                return VERY_GOOD;
            default:
                return VERY_BAD;
        }
    }

    private void setupMoodByType(String moodType) {
        int moodImageResource;
        String moodText;
        int buttonColor;

        switch (moodType) {
            case BAD:
                moodImageResource = R.drawable.ic_mood_bad;
                moodText = "Not Good";
                buttonColor = android.graphics.Color.parseColor("#FF9800");
                break;
            case NEUTRAL:
                moodImageResource = R.drawable.ic_mood_neutral;
                moodText = "Okay";
                buttonColor = android.graphics.Color.parseColor("#FFEB3B");
                break;
            case GOOD:
                moodImageResource = R.drawable.ic_mood_good;
                moodText = "Good";
                buttonColor = android.graphics.Color.parseColor("#8BC34A");
                break;
            case VERY_GOOD:
                moodImageResource = R.drawable.ic_mood_very_good;
                moodText = "Great";
                buttonColor = android.graphics.Color.parseColor("#4CAF50");
                break;
            default: // VERY_BAD
                moodImageResource = R.drawable.ic_mood_very_bad;
                moodText = "Very Bad";
                buttonColor = android.graphics.Color.parseColor("#F44336"); // Red color for very bad
                break;
        }

        // Update UI elements
        selectedMoodImage.setImageResource(moodImageResource);
        moodLabel.setText(moodText);
        submitButton.setText("I Feel " + moodText);
        submitButton.getBackground().setTint(buttonColor);
    }
} 