package com.ti38b.ourpeopleeverywhere.ui;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ti38b.ourpeopleeverywhere.R;

import org.jetbrains.annotations.NotNull;

public class RecyclerViewViewHolder extends RecyclerView.ViewHolder{

        public TextView author;
        public TextView text;
        public TextView rating;
        public TextView date;
        public ImageButton like;
        public Boolean likeButtonPressed;

        public RecyclerViewViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.userNameFieldQuestion);
            text = itemView.findViewById(R.id.textFieldQuestion);
            date = itemView.findViewById(R.id.dateFieldQuestion);
            rating = itemView.findViewById(R.id.ratingFieldQuestion);
            like = itemView.findViewById(R.id.likeButtonQuestion);
        }
    }
