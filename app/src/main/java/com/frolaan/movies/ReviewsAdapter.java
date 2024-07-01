package com.frolaan.movies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewsViewHolder> {

    private List<Review> reviews = new ArrayList<>();
    private static final String POSITIVE_REVIEW = "Позитивный";
    private static final String NEGATIVE_REVIEW = "Негативный";
    private static final String NEUTRAL_REVIEW = "Нейтральный";

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.review_item,
                parent,
                false);
        return new ReviewsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ReviewsViewHolder holder, int position) {
        Review review = reviews.get(position);

        if (review.getReviewAuthor() != null && review.getReview() != null) {
            holder.textViewReviewAuthor.setText(review.getReviewAuthor());
            holder.textViewReview.setText(review.getReview());
        }

        String reviewType = review.getReviewType();
        int backgroundId = android.R.color.darker_gray;;

        switch (reviewType) {
            case POSITIVE_REVIEW:
                backgroundId = android.R.color.holo_green_light;
                break;
            case NEGATIVE_REVIEW:
                backgroundId = android.R.color.holo_red_light;
                break;
        }
        int color = ContextCompat.getColor(holder.itemView.getContext(), backgroundId);
        holder.linearLayoutReview.setBackgroundColor(color);
    }


    @Override
    public int getItemCount() {
        return reviews.size();
    }

    static class ReviewsViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewReview;
        private TextView textViewReviewAuthor;
        private LinearLayout linearLayoutReview;

        public ReviewsViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewReview = itemView.findViewById(R.id.textViewReview);
            textViewReviewAuthor = itemView.findViewById(R.id.textViewReviewAuthor);
            linearLayoutReview = itemView.findViewById(R.id.linearLayoutReview);
        }
    }
}
