package com.example.mentalhealthapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CoachAdapter extends RecyclerView.Adapter<CoachAdapter.ViewHolder> {

    private final List<Coach> coaches;
    private final OnCoachClickListener listener;

    public interface OnCoachClickListener {
        void onChatButtonClick(int position);
        void onCoachClick(int position);
    }

    public CoachAdapter(List<Coach> coaches, OnCoachClickListener listener) {
        this.coaches = coaches;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_coach, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Coach coach = coaches.get(position);
        
        holder.coachName.setText(coach.getName());
        holder.coachSpecialty.setText(coach.getSpecialty());
        holder.coachImage.setImageResource(coach.getImageResourceId());
        
        holder.chatButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onChatButtonClick(position);
            }
        });
        
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCoachClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return coaches.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView coachImage;
        TextView coachName;
        TextView coachSpecialty;
        Button chatButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            coachImage = itemView.findViewById(R.id.coachImage);
            coachName = itemView.findViewById(R.id.coachName);
            coachSpecialty = itemView.findViewById(R.id.coachSpecialty);
            chatButton = itemView.findViewById(R.id.chatButton);
        }
    }
} 