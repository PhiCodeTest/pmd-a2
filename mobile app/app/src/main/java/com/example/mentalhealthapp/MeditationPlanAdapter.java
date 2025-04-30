package com.example.mentalhealthapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MeditationPlanAdapter extends RecyclerView.Adapter<MeditationPlanAdapter.MeditationViewHolder> {

    private List<MeditationPlan> meditationPlans;

    public MeditationPlanAdapter(List<MeditationPlan> meditationPlans) {
        this.meditationPlans = meditationPlans;
    }

    @NonNull
    @Override
    public MeditationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meditation_plan, parent, false);
        return new MeditationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeditationViewHolder holder, int position) {
        MeditationPlan plan = meditationPlans.get(position);
        holder.categoryLabel.setText(plan.getCategory());
        holder.titleText.setText(plan.getTitle());
        holder.durationText.setText(plan.getDuration());
        holder.meditationImage.setImageResource(plan.getImageResource());
    }

    @Override
    public int getItemCount() {
        return meditationPlans.size();
    }

    static class MeditationViewHolder extends RecyclerView.ViewHolder {
        TextView categoryLabel, titleText, durationText;
        ImageView meditationImage;

        public MeditationViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryLabel = itemView.findViewById(R.id.categoryLabel);
            titleText = itemView.findViewById(R.id.titleText);
            durationText = itemView.findViewById(R.id.durationText);
            meditationImage = itemView.findViewById(R.id.meditationImage);
        }
    }
} 