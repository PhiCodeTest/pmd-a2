package com.example.mentalhealthapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TalkWithCoachActivity extends AppCompatActivity implements CoachAdapter.OnCoachClickListener {

    private RecyclerView coachesRecyclerView;
    private CoachAdapter coachAdapter;
    private List<Coach> coaches = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk_with_coach);

        // Setup back button
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        // Setup calendar button
        ImageButton calendarButton = findViewById(R.id.calendarButton);
        calendarButton.setOnClickListener(v -> 
            Toast.makeText(this, "Calendar view coming soon", Toast.LENGTH_SHORT).show()
        );

        // Setup recycler view
        coachesRecyclerView = findViewById(R.id.coachesRecyclerView);
        coachesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load coaches data
        loadCoaches();

        // Setup adapter
        coachAdapter = new CoachAdapter(coaches, this);
        coachesRecyclerView.setAdapter(coachAdapter);
    }

    private void loadCoaches() {
        // Add sample coach data - in a real app, this would come from a database or API
        coaches.add(new Coach("Dr. William Brown, Ph.D", "Clinical Psychologist", R.drawable.coach1));
        coaches.add(new Coach("Coach Emily Wilson", "Certified Mindfulness Coach", R.drawable.coach2));
        coaches.add(new Coach("Dr. Christopher Lee, M.D.", "Psychiatrist", R.drawable.coach3));
        coaches.add(new Coach("Coach Olivia Johnson", "Certified Life Coach", R.drawable.coach4));
        coaches.add(new Coach("Dr. Marcus Evans, Psy.D", "Clinical Psychologist", R.drawable.coach5));
        coaches.add(new Coach("Dr. Lauren Adams", "Counseling Psychologist", R.drawable.coach6));
        coaches.add(new Coach("Coach Daniel Martinez", "Certified Health Coach", R.drawable.coach7));
        coaches.add(new Coach("Coach Jessica Taylor", "Certified Health Coach", R.drawable.coach8));
        coaches.add(new Coach("Dr. Adam Carter, Ph.D.", "Neuropsychologist", R.drawable.coach9));
    }

    @Override
    public void onChatButtonClick(int position) {
        Coach coach = coaches.get(position);
        // Start the ChatActivity with the coach name and image
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("COACH_NAME", coach.getName());
        intent.putExtra("COACH_IMAGE", coach.getImageResourceId());
        startActivity(intent);
    }

    @Override
    public void onCoachClick(int position) {
        Coach coach = coaches.get(position);
        Toast.makeText(this, "View profile of " + coach.getName(), Toast.LENGTH_SHORT).show();
        // TODO: Show coach profile details
    }
} 