package com.frolaan.movies;

import com.google.gson.annotations.SerializedName;

public class Review {

    @SerializedName("type")
    private String reviewType;
    @SerializedName("review")
    private String review;
    @SerializedName("author")
    private String reviewAuthor;

    public Review(String reviewType, String review, String reviewAuthor) {
        this.reviewType = reviewType;
        this.review = review;
        this.reviewAuthor = reviewAuthor;
    }

    public String getReviewType() {
        return reviewType;
    }

    public String getReview() {
        return review;
    }

    public String getReviewAuthor() {
        return reviewAuthor;
    }

    @Override
    public String toString() {
        return "Review{" +
                ", reviewType='" + reviewType + '\'' +
                ", review='" + review + '\'' +
                ", reviewAuthor='" + reviewAuthor + '\'' +
                '}';
    }
}
