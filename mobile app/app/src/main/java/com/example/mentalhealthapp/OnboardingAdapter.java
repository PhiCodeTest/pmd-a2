package com.example.mentalhealthapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder> {

    private Context context;
    private final int[] screenImages = {
            R.drawable.step1,
            R.drawable.step2,
            R.drawable.step3
    };
    private final String[] titles = {
            "Your Personalized Mental\nWellness Companion",
            "Dive and Explore Your\nPath to Wellness",
            "Gain Insights and Track\nProgress Overtime"
    };
    private final String[] descriptions = {
            "Discover personalized mental health plans tailored just for you by our AI. Track your mood and explore a world of wellness resources.",
            "Explore meditation exercises, breathing techniques, articles, courses, journals, and mindfulness resources to find your center.",
            "Gain valuable insights into your well-being with mood tracking, growth area reports, and life balance graphs overtime."
    };

    public OnboardingAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public OnboardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_onboarding, parent, false);
        return new OnboardingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingViewHolder holder, int position) {
        holder.phoneImage.setImageResource(screenImages[position]);
        holder.titleText.setText(titles[position]);
        holder.descriptionText.setText(descriptions[position]);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    static class OnboardingViewHolder extends RecyclerView.ViewHolder {
        ImageView phoneImage;
        TextView titleText;
        TextView descriptionText;

        public OnboardingViewHolder(@NonNull View itemView) {
            super(itemView);
            phoneImage = itemView.findViewById(R.id.phoneImage);
            titleText = itemView.findViewById(R.id.titleText);
            descriptionText = itemView.findViewById(R.id.descriptionText);
        }
    }
} 