package com.example.mentalhealthapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChatActivity extends AppCompatActivity {

    private TextView coachNameTitle;
    private EditText messageInput;
    private ImageButton sendButton;
    private ScrollView messageScrollView;
    private ImageButton backButton;
    private ImageButton callButton;
    private ImageButton videoCallButton;
    private ImageButton moreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // Get the coach name from the intent
        String coachName = getIntent().getStringExtra("COACH_NAME");
        if (coachName == null) {
            coachName = "Dr. William Brown";
        }
        
        // Get the coach image resource ID
        int coachImageResId = getIntent().getIntExtra("COACH_IMAGE", R.drawable.placeholder_coach);
        
        final String finalCoachName = coachName;
        final int finalCoachImageResId = coachImageResId;

        // Initialize views
        coachNameTitle = findViewById(R.id.coachNameTitle);
        messageInput = findViewById(R.id.messageInput);
        sendButton = findViewById(R.id.sendButton);
        messageScrollView = findViewById(R.id.messageScrollView);
        backButton = findViewById(R.id.backButton);
        callButton = findViewById(R.id.callButton);
        videoCallButton = findViewById(R.id.videoCallButton);
        moreButton = findViewById(R.id.moreButton);

        // Set coach name in the toolbar
        coachNameTitle.setText(finalCoachName);

        // Set up click listeners
        backButton.setOnClickListener(v -> finish());

        callButton.setOnClickListener(v -> {
            // Start the CallActivity
            Intent intent = new Intent(this, CallActivity.class);
            intent.putExtra("COACH_NAME", finalCoachName);
            intent.putExtra("COACH_IMAGE", finalCoachImageResId);
            startActivity(intent);
        });
        
        videoCallButton.setOnClickListener(v -> {
            // Start the VideoCallActivity
            VideoCallActivity.start(this, finalCoachName, finalCoachImageResId);
        });

        moreButton.setOnClickListener(v -> 
            Toast.makeText(this, "More options coming soon", Toast.LENGTH_SHORT).show()
        );

        sendButton.setOnClickListener(v -> {
            String message = messageInput.getText().toString().trim();
            if (!message.isEmpty()) {
                // In a real app, this is where you would send the message
                // to your backend or use a proper chat API
                Toast.makeText(this, "Message sent", Toast.LENGTH_SHORT).show();
                messageInput.setText("");
            }
        });

        // Scroll to the bottom of the conversation
        messageScrollView.post(() -> messageScrollView.fullScroll(View.FOCUS_DOWN));
    }
} 