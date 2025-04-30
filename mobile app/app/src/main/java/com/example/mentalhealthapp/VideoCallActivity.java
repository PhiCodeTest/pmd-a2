package com.example.mentalhealthapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VideoCallActivity extends AppCompatActivity {

    private static final String EXTRA_DOCTOR_NAME = "doctor_name";
    private static final String EXTRA_DOCTOR_IMAGE = "doctor_image";

    private ImageView doctorVideoView;
    private ImageView userVideoView;
    private TextView doctorName;
    private TextView callDuration;
    private FloatingActionButton endCallButton;
    private FloatingActionButton cameraButton;
    private FloatingActionButton muteButton;
    private FloatingActionButton speakerButton;
    private ImageButton backButton;
    
    private long startTime = 0L;
    private Handler timerHandler = new Handler();
    private boolean isMuted = false;
    private boolean isCameraOff = false;
    private boolean isSpeakerOn = false;

    /**
     * Start the VideoCallActivity
     * @param context The context to start the activity from
     * @param doctorName The name of the doctor to display
     * @param doctorImageResId The resource ID of the doctor's image (optional, pass 0 if not available)
     */
    public static void start(Context context, String doctorName, int doctorImageResId) {
        Intent intent = new Intent(context, VideoCallActivity.class);
        intent.putExtra(EXTRA_DOCTOR_NAME, doctorName);
        if (doctorImageResId != 0) {
            intent.putExtra(EXTRA_DOCTOR_IMAGE, doctorImageResId);
        }
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_call);
        
        // Initialize views
        doctorVideoView = findViewById(R.id.doctorVideoView);
        userVideoView = findViewById(R.id.userVideoView);
        doctorName = findViewById(R.id.doctorName);
        callDuration = findViewById(R.id.callDuration);
        endCallButton = findViewById(R.id.endCallButton);
        cameraButton = findViewById(R.id.cameraButton);
        muteButton = findViewById(R.id.muteButton);
        speakerButton = findViewById(R.id.speakerButton);
        backButton = findViewById(R.id.backButton);
        
        // Get data from intent
        if (getIntent() != null) {
            String name = getIntent().getStringExtra(EXTRA_DOCTOR_NAME);
            if (name != null && !name.isEmpty()) {
                doctorName.setText(name);
            }
            
            int doctorImageResId = getIntent().getIntExtra(EXTRA_DOCTOR_IMAGE, 0);
            if (doctorImageResId != 0) {
                doctorVideoView.setImageResource(doctorImageResId);
            }
        }
        
        // Start call timer
        startTime = SystemClock.uptimeMillis();
        timerHandler.postDelayed(updateTimerThread, 0);
        
        // Setup click listeners
        setupClickListeners();
    }
    
    private void setupClickListeners() {
        backButton.setOnClickListener(v -> onBackPressed());
        
        endCallButton.setOnClickListener(v -> {
            timerHandler.removeCallbacks(updateTimerThread);
            finish();
        });
        
        muteButton.setOnClickListener(v -> {
            isMuted = !isMuted;
            if (isMuted) {
                muteButton.setImageResource(R.drawable.ic_mic_off);
                Toast.makeText(this, getString(R.string.mic_off), Toast.LENGTH_SHORT).show();
            } else {
                muteButton.setImageResource(R.drawable.ic_mic);
                Toast.makeText(this, getString(R.string.mic_on), Toast.LENGTH_SHORT).show();
            }
        });
        
        cameraButton.setOnClickListener(v -> {
            isCameraOff = !isCameraOff;
            if (isCameraOff) {
                userVideoView.setVisibility(View.INVISIBLE);
                cameraButton.setImageResource(R.drawable.ic_videocam_off);
                Toast.makeText(this, getString(R.string.camera_off), Toast.LENGTH_SHORT).show();
            } else {
                userVideoView.setVisibility(View.VISIBLE);
                cameraButton.setImageResource(R.drawable.ic_videocam);
                Toast.makeText(this, getString(R.string.camera_on), Toast.LENGTH_SHORT).show();
            }
        });
        
        speakerButton.setOnClickListener(v -> {
            isSpeakerOn = !isSpeakerOn;
            if (isSpeakerOn) {
                speakerButton.setImageResource(R.drawable.ic_volume_up);
                Toast.makeText(this, getString(R.string.speaker_on), Toast.LENGTH_SHORT).show();
            } else {
                speakerButton.setImageResource(R.drawable.ic_volume_off);
                Toast.makeText(this, getString(R.string.speaker_off), Toast.LENGTH_SHORT).show();
            }
        });
    }
    
    private Runnable updateTimerThread = new Runnable() {
        public void run() {
            long timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            int seconds = (int) (timeInMilliseconds / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;
            callDuration.setText(String.format("%02d:%02d mins", minutes, seconds));
            timerHandler.postDelayed(this, 1000);
        }
    };
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        timerHandler.removeCallbacks(updateTimerThread);
    }
} 