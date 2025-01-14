package com.frolaan.movies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Rating implements Serializable {
    @SerializedName("kp")
    private double kinopoisk;

    public Rating(double kinopoisk) {
        this.kinopoisk = kinopoisk;
    }

    public double getKinopoisk() {
        return kinopoisk;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "kinopoisk=" + kinopoisk +
                '}';
    }
}
