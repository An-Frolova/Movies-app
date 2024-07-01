package com.frolaan.movies;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Trailer implements Serializable {
    @SerializedName("url")
    private String url;

    @SerializedName("name")
    private String name;

    public Trailer(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        Trailer trailer = (Trailer) obj;
        return this.url.equals(trailer.url) &&
                this.hashCode() == trailer.hashCode();
    }


    @Override
    public int hashCode() {
        return url.hashCode();
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Trailer{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
