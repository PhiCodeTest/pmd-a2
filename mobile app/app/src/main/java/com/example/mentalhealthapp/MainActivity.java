package com.example.mentalhealthapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
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

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private ImageButton moodVeryBad, moodBad, moodNeutral, moodGood, moodVeryGood;
    private CardView chatWithMindyCard, talkWithCoachCard;
    private RecyclerView plansRecyclerView;
    private MeditationPlanAdapter meditationPlanAdapter;
    
    // Add variable for main content container
    private androidx.constraintlayout.widget.ConstraintLayout mainContentLayout;
    private androidx.constraintlayout.widget.ConstraintLayout insightContentLayout;
    private androidx.constraintlayout.widget.ConstraintLayout exploreContentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("MainActivity onCreate called");

        // Initialize bottom navigation
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        // Get main content layout references first
        mainContentLayout = findViewById(R.id.mainContentLayout);
        
        // If mainContentLayout is null, print a warning and try to handle it
        if (mainContentLayout == null) {
            System.out.println("WARNING: mainContentLayout is null in onCreate");
            
            // Try to find it by parent view if possible
            View rootView = findViewById(android.R.id.content);
            if (rootView instanceof ViewGroup) {
                ViewGroup rootViewGroup = (ViewGroup) rootView;
                for (int i = 0; i < rootViewGroup.getChildCount(); i++) {
                    View childView = rootViewGroup.getChildAt(i);
                    if (childView.getId() == R.id.mainContentLayout) {
                        mainContentLayout = (androidx.constraintlayout.widget.ConstraintLayout) childView;
                        System.out.println("Found mainContentLayout through root view traversal");
                        break;
                    }
                }
            }
        } else {
            System.out.println("mainContentLayout found successfully");
            // Ensure it's visible at the start
            mainContentLayout.setVisibility(View.VISIBLE);
        }
        
        // Initialize mood buttons
        moodVeryBad = findViewById(R.id.moodVeryBad);
        moodBad = findViewById(R.id.moodBad);
        moodNeutral = findViewById(R.id.moodNeutral);
        moodGood = findViewById(R.id.moodGood);
        moodVeryGood = findViewById(R.id.moodVeryGood);

        // Initialize chat cards
        chatWithMindyCard = findViewById(R.id.chatWithMindyCard);
        talkWithCoachCard = findViewById(R.id.talkWithCoachCard);

        // Initialize recycler view
        plansRecyclerView = findViewById(R.id.plansRecyclerView);
        plansRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set click listeners
        setMoodClickListeners();
        setChatCardClickListeners();

        // Set up recycler view with meditation plans
        setupMeditationPlans();

        // Check if we should restore a tab state (after recreation)
        int selectedTab = getSharedPreferences("app_prefs", MODE_PRIVATE).getInt("selected_tab", -1);
        System.out.println("Restoring tab selection: " + selectedTab);
        
        // If there's a saved tab state to restore
        if (selectedTab != -1) {
            // Clear the saved preference (one-time use)
            getSharedPreferences("app_prefs", MODE_PRIVATE)
                .edit()
                .remove("selected_tab")
                .apply();
                
            // Set the tab
            bottomNavigationView.setSelectedItemId(selectedTab);
        }
        // Otherwise check if we should show insights tab (coming from mood flow)
        else if (getIntent().getBooleanExtra("SHOW_INSIGHTS", false)) {
            // Select the insights tab
            bottomNavigationView.setSelectedItemId(R.id.navigation_insights);
        } else {
            // Only set the selected item to home if not showing insights
            bottomNavigationView.setSelectedItemId(R.id.navigation_home);
            // Explicitly ensure home content is visible
            if (mainContentLayout != null) {
                mainContentLayout.setVisibility(View.VISIBLE);
            }
        }
    }

    private void setMoodClickListeners() {
        moodVeryBad.setOnClickListener(v -> launchMoodTracker("Very Bad"));
        moodBad.setOnClickListener(v -> launchMoodTracker("Bad"));
        moodNeutral.setOnClickListener(v -> launchMoodTracker("Neutral"));
        moodGood.setOnClickListener(v -> launchMoodTracker("Good"));
        moodVeryGood.setOnClickListener(v -> launchMoodTracker("Very Good"));
    }

    private void launchMoodTracker(String mood) {
        Intent intent = new Intent(this, MoodTrackerActivity.class);
        intent.putExtra("MOOD_TYPE", mood);
        startActivity(intent);
    }

    private void setChatCardClickListeners() {
        chatWithMindyCard.setOnClickListener(v -> {
            // Navigate to chat with Mindy screen
            Intent intent = new Intent(this, ChatActivity.class);
            startActivity(intent);
        });

        talkWithCoachCard.setOnClickListener(v -> {
            // Navigate to talk with coach screen
            Intent intent = new Intent(this, TalkWithCoachActivity.class);
            startActivity(intent);
        });
    }

    private void setupMeditationPlans() {
        List<MeditationPlan> meditationPlans = new ArrayList<>();
        
        meditationPlans.add(new MeditationPlan(
                "Mindfulness",
                "Intro to Meditation",
                "8 mins",
                R.drawable.meditation
        ));
        
        meditationPlans.add(new MeditationPlan(
                "Techniques",
                "Mindfulness Techniques for Beginners",
                "7 mins",
                R.drawable.mindfulness
        ));
        
        meditationPlans.add(new MeditationPlan(
                "Breathing",
                "Deep Breath Dynamics",
                "5 mins",
                R.drawable.breathing
        ));
        
        meditationPlans.add(new MeditationPlan(
                "Self-Care",
                "What activities usually make you feel good?",
                "10 mins",
                R.drawable.selfcare
        ));
        
        meditationPlans.add(new MeditationPlan(
                "Meditation",
                "Gratitude Meditation",
                "6 mins",
                R.drawable.gratitude
        ));

        meditationPlanAdapter = new MeditationPlanAdapter(meditationPlans);
        plansRecyclerView.setAdapter(meditationPlanAdapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        
        System.out.println("onNavigationItemSelected called with itemId: " + itemId);
        
        if (itemId == R.id.navigation_home) {
            System.out.println("navigation_home selected");
            
            // Check if we're navigating from another tab (not already on home)
            if (bottomNavigationView.getSelectedItemId() != R.id.navigation_home) {
                System.out.println("Navigating from another tab to home - recreating activity");
                // Save the current tab to preferences so we can restore it
                getSharedPreferences("app_prefs", MODE_PRIVATE)
                    .edit()
                    .putInt("selected_tab", R.id.navigation_home)
                    .apply();
                    
                // Simply recreate the activity - this is a brute force approach but guarantees a fresh view
                recreate();
                return true;
            }
            
            System.out.println("Already on home tab or initial load");
            System.out.println("Showing main content...");
            showMainContent();
            return true;
        } else if (itemId == R.id.navigation_explore) {
            System.out.println("navigation_explore selected"); 
            System.out.println("Showing explore content...");
            showExploreContent();
            return true;
        } else if (itemId == R.id.navigation_sleep) {
            System.out.println("navigation_sleep selected");
            System.out.println("Showing sleep toast message...");
            Toast.makeText(this, "Sleep", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.navigation_insights) {
            System.out.println("navigation_insights selected");
            System.out.println("Showing insight content...");
            showInsightContent();
            return true;
        } else if (itemId == R.id.navigation_account) {
            System.out.println("navigation_account selected");
            System.out.println("Showing account toast message...");
            Toast.makeText(this, "Account", Toast.LENGTH_SHORT).show();
            return true;
        }
        
        System.out.println("Unknown navigation item selected");
        return false;
    }
    
    // Add methods to show/hide content layouts
    private void showMainContent() {
        System.out.println("showMainContent() called");
        
        // Re-find the mainContentLayout if it's null
        if (mainContentLayout == null) {
            System.out.println("mainContentLayout is null, finding it again");
            mainContentLayout = findViewById(R.id.mainContentLayout);
        }
        
        // Hide other content views
        if (insightContentLayout != null) {
            insightContentLayout.setVisibility(View.GONE);
        }
        
        if (exploreContentLayout != null) {
            exploreContentLayout.setVisibility(View.GONE);
        }
        
        // Show main content
        if (mainContentLayout != null) {
            System.out.println("Setting main content to VISIBLE");
            mainContentLayout.setVisibility(View.VISIBLE);
            
            // Make sure children are properly initialized
            initializeMainContent();
        } else {
            System.out.println("ERROR: mainContentLayout is still null after attempting to find it");
        }
    }
    
    // Add new method to make sure main content is properly initialized
    private void initializeMainContent() {
        System.out.println("Initializing main content elements");
        
        // Re-initialize mood buttons if they're null
        if (moodVeryBad == null) moodVeryBad = findViewById(R.id.moodVeryBad);
        if (moodBad == null) moodBad = findViewById(R.id.moodBad);
        if (moodNeutral == null) moodNeutral = findViewById(R.id.moodNeutral);
        if (moodGood == null) moodGood = findViewById(R.id.moodGood);
        if (moodVeryGood == null) moodVeryGood = findViewById(R.id.moodVeryGood);

        // Re-initialize chat cards if they're null
        if (chatWithMindyCard == null) chatWithMindyCard = findViewById(R.id.chatWithMindyCard);
        if (talkWithCoachCard == null) talkWithCoachCard = findViewById(R.id.talkWithCoachCard);

        // Re-initialize recycler view if it's null
        if (plansRecyclerView == null) {
            plansRecyclerView = findViewById(R.id.plansRecyclerView);
        }
        
        // Ensure all these elements are visible
        if (moodVeryBad != null) moodVeryBad.setVisibility(View.VISIBLE);
        if (moodBad != null) moodBad.setVisibility(View.VISIBLE);
        if (moodNeutral != null) moodNeutral.setVisibility(View.VISIBLE);
        if (moodGood != null) moodGood.setVisibility(View.VISIBLE);
        if (moodVeryGood != null) moodVeryGood.setVisibility(View.VISIBLE);
        
        if (chatWithMindyCard != null) chatWithMindyCard.setVisibility(View.VISIBLE);
        if (talkWithCoachCard != null) talkWithCoachCard.setVisibility(View.VISIBLE);
        
        if (plansRecyclerView != null) {
            plansRecyclerView.setVisibility(View.VISIBLE);
            
            // Ensure RecyclerView has a layout manager
            if (plansRecyclerView.getLayoutManager() == null) {
                plansRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            }
            
            // If the adapter is null, recreate it
            if (meditationPlanAdapter == null) {
                setupMeditationPlans();
            } else {
                plansRecyclerView.setAdapter(meditationPlanAdapter);
            }
        }
        
        // Also make sure any potential parent containers within mainContentLayout are visible
        // Find any LinearLayout, RelativeLayout, ConstraintLayout, etc. that might be parents
        // of our UI elements
        if (mainContentLayout != null) {
            System.out.println("Ensuring all child views of mainContentLayout are visible");
            makeAllChildrenVisible(mainContentLayout);
        }
        
        // Refresh click listeners just to be safe
        setMoodClickListeners();
        setChatCardClickListeners();
    }
    
    // Recursive method to make all children of a ViewGroup visible
    private void makeAllChildrenVisible(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View child = viewGroup.getChildAt(i);
            if (child.getVisibility() != View.VISIBLE) {
                System.out.println("Setting child view " + child.getId() + " to VISIBLE");
                child.setVisibility(View.VISIBLE);
            }
            
            // Recursively process any ViewGroups
            if (child instanceof ViewGroup) {
                makeAllChildrenVisible((ViewGroup) child);
            }
        }
    }
    
    private void showInsightContent() {
        System.out.println("showInsightContent() called");
        
        try {
            // Check if insight layout is already inflated
            if (insightContentLayout == null) {
                System.out.println("Insight layout not yet inflated, inflating...");
                
                // Inflate the insight layout
                View insightView = getLayoutInflater().inflate(R.layout.activity_insight, null);
                androidx.coordinatorlayout.widget.CoordinatorLayout container = findViewById(R.id.container);
                
                if (container != null) {
                    // Set appropriate layout parameters
                    androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams params = 
                        new androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams(
                            androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams.MATCH_PARENT,
                            androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams.MATCH_PARENT
                        );
                    insightView.setLayoutParams(params);
                    
                    // Add to container
                    container.addView(insightView);
                    insightContentLayout = findViewById(R.id.insightContentLayout);
                    
                    // Initialize insight view components
                    initializeInsightViews();
                } else {
                    System.out.println("Error: Container not found");
                    Toast.makeText(this, "Error loading insights view", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            
            // Hide main content
            if (mainContentLayout != null) {
                mainContentLayout.setVisibility(View.GONE);
            }
            
            // Hide explore content if it exists
            if (exploreContentLayout != null) {
                exploreContentLayout.setVisibility(View.GONE);
            }
            
            // Show insight content
            insightContentLayout.setVisibility(View.VISIBLE);
            
        } catch (Exception e) {
            System.out.println("Exception in showInsightContent: " + e.getMessage());
            e.printStackTrace();
            Toast.makeText(this, "Error loading insights", Toast.LENGTH_SHORT).show();
            
            // Fall back to main content
            if (mainContentLayout != null) {
                mainContentLayout.setVisibility(View.VISIBLE);
            }
        }
    }
    
    private void initializeInsightViews() {
        // Initialize and set up insight page components similar to InsightActivity
        ImageButton moreButton = findViewById(R.id.moreButton);
        ImageButton cameraButton = findViewById(R.id.cameraButton);
        ImageButton qrButton = findViewById(R.id.qrButton);
        ImageButton cameraButton2 = findViewById(R.id.cameraButton2);
        ImageButton qrButton2 = findViewById(R.id.qrButton2);
        ImageButton prevWeekButton = findViewById(R.id.prevWeekButton);
        ImageButton nextWeekButton = findViewById(R.id.nextWeekButton);
        TextView weeklyPeriod = findViewById(R.id.weeklyPeriod);
        TextView monthlyPeriod = findViewById(R.id.monthlyPeriod);
        TextView yearlyPeriod = findViewById(R.id.yearlyPeriod);
        TextView dateRangeText = findViewById(R.id.dateRangeText);
        
        // Set click listeners
        moreButton.setOnClickListener(v -> {
            Toast.makeText(this, "More options", Toast.LENGTH_SHORT).show();
        });
        
        weeklyPeriod.setOnClickListener(v -> {
            setPeriodSelection("WEEKLY");
        });
        
        monthlyPeriod.setOnClickListener(v -> {
            setPeriodSelection("MONTHLY");
        });
        
        yearlyPeriod.setOnClickListener(v -> {
            setPeriodSelection("YEARLY");
        });
    }
    
    private void setPeriodSelection(String periodType) {
        // Reset all to unselected
        TextView weeklyPeriod = findViewById(R.id.weeklyPeriod);
        TextView monthlyPeriod = findViewById(R.id.monthlyPeriod);
        TextView yearlyPeriod = findViewById(R.id.yearlyPeriod);
        TextView dateRangeText = findViewById(R.id.dateRangeText);
        
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
            case "WEEKLY":
                selectedPeriod = weeklyPeriod;
                dateRange = "Dec 16 - Dec 22, 2024";
                break;
                
            case "MONTHLY":
                selectedPeriod = monthlyPeriod;
                dateRange = "December 2024";
                break;
                
            case "YEARLY":
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

    private void showExploreContent() {
        System.out.println("showExploreContent() called");
        
        try {
            // Check if explore layout is already inflated
            if (exploreContentLayout == null) {
                System.out.println("Explore layout not yet inflated, inflating...");
                
                // Inflate the explore layout
                View exploreView = getLayoutInflater().inflate(R.layout.activity_explore, null);
                androidx.coordinatorlayout.widget.CoordinatorLayout container = findViewById(R.id.container);
                
                if (container != null) {
                    // Set appropriate layout parameters
                    androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams params = 
                        new androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams(
                            androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams.MATCH_PARENT,
                            androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams.MATCH_PARENT
                        );
                    exploreView.setLayoutParams(params);
                    
                    // Add to container
                    container.addView(exploreView);
                    exploreContentLayout = findViewById(R.id.exploreContentLayout);
                    
                    // Initialize explore view components
                    initializeExploreViews();
                } else {
                    System.out.println("Error: Container not found in showExploreContent");
                    Toast.makeText(this, "Error loading explore view", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            
            // Hide main content
            if (mainContentLayout != null) {
                mainContentLayout.setVisibility(View.GONE);
            }
            
            // Hide insight content if it exists
            if (insightContentLayout != null) {
                insightContentLayout.setVisibility(View.GONE);
            }
            
            // Show explore content
            exploreContentLayout.setVisibility(View.VISIBLE);
            
        } catch (Exception e) {
            System.out.println("Exception in showExploreContent: " + e.getMessage());
            e.printStackTrace();
            Toast.makeText(this, "Error loading explore view", Toast.LENGTH_SHORT).show();
            
            // Fall back to main content
            if (mainContentLayout != null) {
                mainContentLayout.setVisibility(View.VISIBLE);
            }
        }
    }
    
    private void initializeExploreViews() {
        // Initialize and set up explore page components similar to ExploreActivity
        ImageButton searchButton = findViewById(R.id.searchButton);
        ImageButton favoriteButton = findViewById(R.id.favoriteButton);
        
        CardView meditationsCard = findViewById(R.id.meditationsCard);
        CardView breathingCard = findViewById(R.id.breathingCard);
        CardView articlesCard = findViewById(R.id.articlesCard);
        CardView testsCard = findViewById(R.id.testsCard);
        CardView journalCard = findViewById(R.id.journalCard);
        CardView notepadCard = findViewById(R.id.notepadCard);
        CardView affirmationsCard = findViewById(R.id.affirmationsCard);
        CardView quotesCard = findViewById(R.id.quotesCard);
        CardView tipsCard = findViewById(R.id.tipsCard);
        
        // Set up the RecyclerView with horizontal layout manager and adapter
        RecyclerView meditationProgramsRecycler = findViewById(R.id.meditationProgramsRecycler);
        if (meditationProgramsRecycler != null) {
            // Configure RecyclerView for horizontal scrolling
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            meditationProgramsRecycler.setLayoutManager(layoutManager);
            
            // Create list of meditation programs
            List<MeditationProgram> programs = new ArrayList<>();
            programs.add(new MeditationProgram("Stress", "Management", "8 mins", R.drawable.stressmanagement));
            programs.add(new MeditationProgram("Mood Boost", "Blueprint", "9 mins", R.drawable.moodboosblueprint));
            programs.add(new MeditationProgram("Anxiety Relief", "Meditation", "10 mins", R.drawable.anxiety));
            
            // Create and set the adapter
            MeditationProgramAdapter adapter = new MeditationProgramAdapter(programs);
            meditationProgramsRecycler.setAdapter(adapter);
            
            // Make RecyclerView visible
            meditationProgramsRecycler.setVisibility(View.VISIBLE);
        }
        
        // Set click listeners
        if (searchButton != null) {
            searchButton.setOnClickListener(v -> {
                Toast.makeText(this, "Search feature coming soon", Toast.LENGTH_SHORT).show();
            });
        }
        
        if (favoriteButton != null) {
            favoriteButton.setOnClickListener(v -> {
                navigateToFavorites();
            });
        }
        
        // Set up category card click listeners
        if (meditationsCard != null) {
            meditationsCard.setOnClickListener(v -> navigateToCategory("Meditations"));
        }
        
        if (breathingCard != null) {
            breathingCard.setOnClickListener(v -> navigateToCategory("Breathing"));
        }
        
        if (articlesCard != null) {
            articlesCard.setOnClickListener(v -> navigateToCategory("Articles"));
        }
        
        if (testsCard != null) {
            testsCard.setOnClickListener(v -> navigateToCategory("Tests"));
        }
        
        if (journalCard != null) {
            journalCard.setOnClickListener(v -> navigateToCategory("Smart Journal"));
        }
        
        if (notepadCard != null) {
            notepadCard.setOnClickListener(v -> navigateToCategory("Notepad"));
        }
        
        if (affirmationsCard != null) {
            affirmationsCard.setOnClickListener(v -> navigateToCategory("Affirmations"));
        }
        
        if (quotesCard != null) {
            quotesCard.setOnClickListener(v -> navigateToQuotes());
        }
        
        if (tipsCard != null) {
            tipsCard.setOnClickListener(v -> navigateToCategory("Tips"));
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
                Toast.makeText(MainActivity.this, program.getTitle() + " " + program.getSubtitle(), Toast.LENGTH_SHORT).show();
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
    
    private void navigateToCategory(String category) {
        Toast.makeText(this, category + " coming soon", Toast.LENGTH_SHORT).show();
        
        // For demonstration, navigate to specific activities based on category
        if (category.equals("Meditations")) {
            Intent intent = new Intent(this, MeditationActivity.class);
            startActivity(intent);
        }
    }

    // Add a method to check main content after a short delay
    private void checkMainContentAfterDelay() {
        // Post with delay to allow UI to update
        new android.os.Handler().postDelayed(() -> {
            System.out.println("Performing delayed main content check");
            
            // If main content layout is null or has no visible children, try recreating content
            if (mainContentLayout == null) {
                System.out.println("Main content is null in delayed check, trying to find it");
                mainContentLayout = findViewById(R.id.mainContentLayout);
            }
            
            if (mainContentLayout != null) {
                // Count visible children
                int visibleChildren = 0;
                
                if (mainContentLayout instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) mainContentLayout;
                    for (int i = 0; i < viewGroup.getChildCount(); i++) {
                        if (viewGroup.getChildAt(i).getVisibility() == View.VISIBLE) {
                            visibleChildren++;
                        }
                    }
                    
                    System.out.println("Main content has " + visibleChildren + " visible children out of " + 
                                      viewGroup.getChildCount() + " total");
                    
                    // If no visible children, try to recreate the layout
                    if (visibleChildren == 0 && viewGroup.getChildCount() > 0) {
                        System.out.println("WARNING: Main content appears to be blank, attempting emergency recovery");
                        
                        // Try detaching and reattaching the main content first before recreating the activity
                        tryReattachMainContent();
                    }
                }
                
                // Check if the main content is showing up on screen
                mainContentLayout.post(() -> {
                    if (mainContentLayout.getWidth() == 0 || mainContentLayout.getHeight() == 0) {
                        System.out.println("WARNING: Main content has zero size, attempting forced measure pass");
                        forceMainContentMeasure();
                    }
                });
            }
        }, 500); // 500ms delay to allow UI to render
    }

    // A method to try detaching and reattaching main content
    private void tryReattachMainContent() {
        System.out.println("Attempting to detach and reattach main content");
        
        if (mainContentLayout != null && mainContentLayout.getParent() instanceof ViewGroup) {
            ViewGroup parent = (ViewGroup) mainContentLayout.getParent();
            
            try {
                // Save the layout parameters
                ViewGroup.LayoutParams layoutParams = mainContentLayout.getLayoutParams();
                
                // Detach from parent
                parent.removeView(mainContentLayout);
                
                // Create a short delay to allow the UI thread to process
                mainContentLayout.post(() -> {
                    // Reattach to parent
                    parent.addView(mainContentLayout, layoutParams);
                    
                    // Show toast to user
                    Toast.makeText(MainActivity.this, 
                                 "Refreshing view...", 
                                 Toast.LENGTH_SHORT).show();
                    
                    // Make visible and reinitialize
                    mainContentLayout.setVisibility(View.VISIBLE);
                    mainContentLayout.bringToFront();
                    initializeMainContent();
                    
                    // Force layout
                    mainContentLayout.requestLayout();
                    mainContentLayout.invalidate();
                    parent.requestLayout();
                    parent.invalidate();
                });
            } catch (Exception e) {
                System.out.println("Error during detach/reattach: " + e.getMessage());
                e.printStackTrace();
                
                // Fallback to recreating the activity
                recreate();
            }
        } else {
            // If we can't detach/reattach, recreate the activity
            System.out.println("Cannot reattach main content, recreating activity");
            recreate();
        }
    }

    // Force a measure pass on the main content
    private void forceMainContentMeasure() {
        System.out.println("Forcing measure pass on main content");
        
        if (mainContentLayout != null) {
            // Get the display metrics to determine proper size
            android.util.DisplayMetrics displayMetrics = new android.util.DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            
            int screenWidth = displayMetrics.widthPixels;
            int screenHeight = displayMetrics.heightPixels;
            
            // Force measure with screen dimensions
            int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(screenWidth, View.MeasureSpec.EXACTLY);
            int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(screenHeight, View.MeasureSpec.EXACTLY);
            
            mainContentLayout.measure(widthMeasureSpec, heightMeasureSpec);
            mainContentLayout.layout(0, 0, mainContentLayout.getMeasuredWidth(), mainContentLayout.getMeasuredHeight());
            
            // Force redraw
            mainContentLayout.invalidate();
        }
    }
}