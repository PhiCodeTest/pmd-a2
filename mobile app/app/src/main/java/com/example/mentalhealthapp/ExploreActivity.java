package com.example.mentalhealthapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ExploreActivity extends AppCompatActivity {

    private ImageButton searchButton;
    private ImageButton favoriteButton;
    private CardView meditationsCard;
    private CardView breathingCard;
    private CardView articlesCard;
    private CardView testsCard;
    private CardView journalCard;
    private CardView notepadCard;
    private CardView affirmationsCard;
    private CardView quotesCard;
    private CardView tipsCard;
    private RecyclerView meditationProgramsRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        // Initialize UI components
        initViews();
        setupListeners();
        
        // Setup bottom navigation
        setupBottomNavigation();
    }

    private void initViews() {
        searchButton = findViewById(R.id.searchButton);
        favoriteButton = findViewById(R.id.favoriteButton);
        
        meditationsCard = findViewById(R.id.meditationsCard);
        breathingCard = findViewById(R.id.breathingCard);
        articlesCard = findViewById(R.id.articlesCard);
        testsCard = findViewById(R.id.testsCard);
        journalCard = findViewById(R.id.journalCard);
        notepadCard = findViewById(R.id.notepadCard);
        affirmationsCard = findViewById(R.id.affirmationsCard);
        quotesCard = findViewById(R.id.quotesCard);
        tipsCard = findViewById(R.id.tipsCard);
        
        // Set up the RecyclerView with horizontal layout manager and adapter
        meditationProgramsRecycler = findViewById(R.id.meditationProgramsRecycler);
        
        // Configure RecyclerView for horizontal scrolling
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        meditationProgramsRecycler.setLayoutManager(layoutManager);
        
        // Create and set meditation programs adapter
        System.out.println("Create and set meditation programs adapter");
        List<MeditationProgram> meditationPrograms = getMeditationPrograms();
        MeditationProgramAdapter adapter = new MeditationProgramAdapter(meditationPrograms);
        meditationProgramsRecycler.setAdapter(adapter);
        
        // Make RecyclerView visible (it was previously set to GONE)
        meditationProgramsRecycler.setVisibility(View.VISIBLE);
    }
    
    // Create a list of meditation programs for the adapter
    private List<MeditationProgram> getMeditationPrograms() {
        List<MeditationProgram> programs = new ArrayList<>();
        
        programs.add(new MeditationProgram("Stress", "Management", "8 mins", R.drawable.ic_stress_management));
        programs.add(new MeditationProgram("Mood Boost", "Blueprint", "9 mins", R.drawable.ic_mood_boost));
        programs.add(new MeditationProgram("Anxiety Relief", "Meditation", "10 mins", R.drawable.ic_anxiety_relief));
        System.out.println("getMeditationPrograms");
        return programs;
    }

    private void setupListeners() {
        searchButton.setOnClickListener(v -> {
            // Search functionality
            Toast.makeText(ExploreActivity.this, "Search feature coming soon", Toast.LENGTH_SHORT).show();
        });

        favoriteButton.setOnClickListener(v -> {
            // Navigate to Favorites activity
            navigateToFavorites();
        });

        meditationsCard.setOnClickListener(v -> {
            navigateToCategory("Meditations");
        });

        breathingCard.setOnClickListener(v -> {
            navigateToCategory("Breathing");
        });

        articlesCard.setOnClickListener(v -> {
            navigateToCategory("Articles");
        });

        testsCard.setOnClickListener(v -> {
            navigateToCategory("Tests");
        });

        journalCard.setOnClickListener(v -> {
            navigateToCategory("Smart Journal");
        });

        notepadCard.setOnClickListener(v -> {
            navigateToCategory("Notepad");
        });

        affirmationsCard.setOnClickListener(v -> {
            navigateToCategory("Affirmations");
        });

        quotesCard.setOnClickListener(v -> {
            navigateToQuotes();
        });

        tipsCard.setOnClickListener(v -> {
            navigateToCategory("Tips");
        });
    }

    private void navigateToCategory(String category) {
        Toast.makeText(this, category + " coming soon", Toast.LENGTH_SHORT).show();
        
        // For demonstration, navigate to specific activities based on category
        if (category.equals("Meditations")) {
            Intent intent = new Intent(this, MeditationActivity.class);
            startActivity(intent);
        }
    }

    private void navigateToFavorites() {
        Intent intent = new Intent(this, FavoritesActivity.class);
        startActivity(intent);
    }

    private void navigateToQuotes() {
        Intent intent = new Intent(this, QuotesActivity.class);
        startActivity(intent);
    }

    private void setupBottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        if (bottomNavigationView != null) {
            bottomNavigationView.setSelectedItemId(R.id.nav_meditation);
            
            bottomNavigationView.setOnItemSelectedListener(item -> {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (itemId == R.id.nav_meditation) {
                    return true;
                } else if (itemId == R.id.nav_chat) {
                    startActivity(new Intent(getApplicationContext(), ChatActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (itemId == R.id.nav_insights) {
                    startActivity(new Intent(getApplicationContext(), InsightActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                } else if (itemId == R.id.nav_profile) {
                    // Navigate to account activity
                    Toast.makeText(this, "Profile feature coming soon", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            });
        }
    }
    
    // Model class for Meditation Program
    private static class MeditationProgram {
        private String title;
        private String subtitle;
        private String duration;
        private int imageResourceId;
        
        public MeditationProgram(String title, String subtitle, String duration, int imageResourceId) {
            this.title = title;
            this.subtitle = subtitle;
            this.duration = duration;
            this.imageResourceId = imageResourceId;
        }
        
        public String getTitle() {
            return title;
        }
        
        public String getSubtitle() {
            return subtitle;
        }
        
        public String getDuration() {
            return duration;
        }
        
        public int getImageResourceId() {
            return imageResourceId;
        }
    }
    
    // Adapter for the horizontal meditation programs RecyclerView
    private class MeditationProgramAdapter extends RecyclerView.Adapter<MeditationProgramAdapter.ViewHolder> {
        
        private List<MeditationProgram> meditationPrograms;
        
        public MeditationProgramAdapter(List<MeditationProgram> meditationPrograms) {
            this.meditationPrograms = meditationPrograms;
        }
        
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_meditation_program, parent, false);
            return new ViewHolder(view);
        }
        
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            MeditationProgram program = meditationPrograms.get(position);
            
            holder.titleTextView.setText(program.getTitle());
            holder.subtitleTextView.setText(program.getSubtitle());
            holder.durationTextView.setText(program.getDuration());
            holder.imageView.setImageResource(program.getImageResourceId());
            
            holder.cardView.setOnClickListener(v -> {
                Toast.makeText(ExploreActivity.this, program.getTitle() + " " + program.getSubtitle(), Toast.LENGTH_SHORT).show();
            });
        }
        
        @Override
        public int getItemCount() {
            return meditationPrograms.size();
        }
        
        public class ViewHolder extends RecyclerView.ViewHolder {
            CardView cardView;
            ImageView imageView;
            TextView titleTextView;
            TextView subtitleTextView;
            TextView durationTextView;
            
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                cardView = itemView.findViewById(R.id.cardView);
                imageView = itemView.findViewById(R.id.imageView);
                titleTextView = itemView.findViewById(R.id.titleTextView);
                subtitleTextView = itemView.findViewById(R.id.subtitleTextView);
                durationTextView = itemView.findViewById(R.id.durationTextView);
            }
        }
    }
} 