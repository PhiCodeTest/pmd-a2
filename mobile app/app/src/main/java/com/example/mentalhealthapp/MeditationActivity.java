package com.example.mentalhealthapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MeditationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation);

        // Back button navigation
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        // Favorite button
        ImageButton favoriteButton = findViewById(R.id.favoriteButton);
        favoriteButton.setOnClickListener(v -> {
            // Toggle favorite state
            v.setSelected(!v.isSelected());
        });

        // Share button
        ImageButton shareButton = findViewById(R.id.shareButton);
        shareButton.setOnClickListener(v -> {
            // Implement share functionality
            // Intent shareIntent = new Intent(Intent.ACTION_SEND);
            // shareIntent.setType("text/plain");
            // shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this meditation session!");
            // startActivity(Intent.createChooser(shareIntent, "Share via"));
        });

        // Start meditation button
        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(v -> {
            // Start meditation playback
            // TODO: Implement meditation playback
        });

        // Load meditation data
        TextView titleTextView = findViewById(R.id.meditationTitle);
        TextView durationTextView = findViewById(R.id.meditationDuration);
        TextView descriptionTextView = findViewById(R.id.meditationDescription);

        // Set data (in a real app, this would come from intent extras or a database)
        titleTextView.setText(R.string.intro_to_meditation);
        durationTextView.setText(R.string.meditation_duration);
        descriptionTextView.setText(R.string.meditation_description);
    }
} 