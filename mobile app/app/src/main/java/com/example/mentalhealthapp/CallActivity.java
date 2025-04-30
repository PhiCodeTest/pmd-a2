package com.example.mentalhealthapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.Locale;

public class CallActivity extends AppCompatActivity {

    private TextView doctorNameText;
    private TextView callDurationText;
    private ShapeableImageView doctorImage;
    private ImageButton backButton;
    private FloatingActionButton endCallButton;
    private FloatingActionButton muteButton;
    private FloatingActionButton speakerButton;
    
    private Handler durationHandler;
    private int seconds = 0;
    private boolean isMuted = false;
    private boolean isSpeakerOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        // Get the coach name from the intent
        String coachName = getIntent().getStringExtra("COACH_NAME");
        if (coachName == null) {
            coachName = "Dr. William Brown";
        }
        
        // Get the coach image resource ID
        int coachImageResId = getIntent().getIntExtra("COACH_IMAGE", R.drawable.placeholder_coach);

        // Initialize views
        doctorNameText = findViewById(R.id.doctorName);
        callDurationText = findViewById(R.id.callDuration);
        doctorImage = findViewById(R.id.doctorImage);
        backButton = findViewById(R.id.backButton);
        endCallButton = findViewById(R.id.endCallButton);
        muteButton = findViewById(R.id.muteButton);
        speakerButton = findViewById(R.id.speakerButton);

        // Set the doctor's name and image
        doctorNameText.setText(coachName);
        doctorImage.setImageResource(coachImageResId);

        // Initialize call timer
        durationHandler = new Handler(Looper.getMainLooper());
        startCallTimer();

        // Set click listeners
        backButton.setOnClickListener(v -> onBackPressed());
        
        endCallButton.setOnClickListener(v -> {
            // End the call and finish the activity
            Toast.makeText(this, "Call ended", Toast.LENGTH_SHORT).show();
            finish();
        });
        
        muteButton.setOnClickListener(v -> {
            isMuted = !isMuted;
            if (isMuted) {
                muteButton.setImageResource(R.drawable.ic_mic_off);
                Toast.makeText(this, "Call muted", Toast.LENGTH_SHORT).show();
            } else {
                muteButton.setImageResource(R.drawable.ic_mic);
                Toast.makeText(this, "Call unmuted", Toast.LENGTH_SHORT).show();
            }
        });
        
        speakerButton.setOnClickListener(v -> {
            isSpeakerOn = !isSpeakerOn;
            if (isSpeakerOn) {
                speakerButton.setImageResource(R.drawable.ic_volume_up);
                Toast.makeText(this, "Speaker on", Toast.LENGTH_SHORT).show();
            } else {
                speakerButton.setImageResource(R.drawable.ic_volume_down);
                Toast.makeText(this, "Speaker off", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startCallTimer() {
        Runnable updateTimer = new Runnable() {
            @Override
            public void run() {
                seconds++;
                int minutes = seconds / 60;
                int secs = seconds % 60;
                callDurationText.setText(String.format(Locale.getDefault(), "%02d:%02d mins", minutes, secs));
                durationHandler.postDelayed(this, 1000);
            }
        };
        durationHandler.post(updateTimer);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (durationHandler != null) {
            durationHandler.removeCallbacksAndMessages(null);
        }
    }
} 