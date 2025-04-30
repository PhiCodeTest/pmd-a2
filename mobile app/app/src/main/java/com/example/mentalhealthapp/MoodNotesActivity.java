package com.example.mentalhealthapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MoodNotesActivity extends AppCompatActivity {

    private ImageButton closeButton;
    private EditText notesEditText;
    private Button saveButton;
    private String moodType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_notes);

        // Initialize views
        closeButton = findViewById(R.id.closeButton);
        notesEditText = findViewById(R.id.notesEditText);
        saveButton = findViewById(R.id.saveButton);

        // Get the mood data from the intent
        if (getIntent().hasExtra("MOOD_TYPE")) {
            moodType = getIntent().getStringExtra("MOOD_TYPE");
        }
        
        // Check if there are existing notes to display
        if (getIntent().hasExtra("MOOD_NOTES")) {
            String existingNotes = getIntent().getStringExtra("MOOD_NOTES");
            if (existingNotes != null && !existingNotes.isEmpty()) {
                notesEditText.setText(existingNotes);
                // Place cursor at the end of the text
                notesEditText.setSelection(existingNotes.length());
            }
        }

        // Set click listeners
        closeButton.setOnClickListener(v -> {
            // Go back to mood tracker
            goBackToMoodTracker();
        });
        
        saveButton.setOnClickListener(v -> {
            saveMoodWithNotes();
        });
    }

    @Override
    public void onBackPressed() {
        // Go back to mood tracker
        goBackToMoodTracker();
    }
    
    private void goBackToMoodTracker() {
        Intent intent = new Intent(this, MoodTrackerActivity.class);
        // Pass back the current mood type
        intent.putExtra("MOOD_TYPE", moodType);
        // Also pass any notes the user might have entered
        intent.putExtra("MOOD_NOTES", notesEditText.getText().toString().trim());
        startActivity(intent);
        finish();
    }

    private void saveMoodWithNotes() {
        // Here you would typically save the mood and notes to a database
        Toast.makeText(this, "Notes saved!", Toast.LENGTH_SHORT).show();
        
        // Navigate to the Insight page (no need to pass any data, everything is hardcoded)
        navigateToInsightPage();
    }
    
    private void navigateToInsightPage() {
        Intent intent = new Intent(this, MainActivity.class);
        // Add a flag to tell MainActivity to show the insights tab
        intent.putExtra("SHOW_INSIGHTS", true);
        // Clear back stack so user can't go back to notes
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish(); // Finish this activity as we're moving forward in the flow
    }
} 