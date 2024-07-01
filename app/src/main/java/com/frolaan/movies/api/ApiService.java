package com.frolaan.movies.api;

import com.frolaan.movies.MovieResponse;
import com.frolaan.movies.ReviewResponse;
import com.frolaan.movies.TrailerResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("v1.4/movie?token=79ZN2FC-C77M7S5-PKGK9QV-6CSEFZ4&rating.kp=7-10&sortField=votes.kp&sortType=-1&limit=30")
    Single<MovieResponse> loadMovies(@Query("page") int page);

    @GET("v1.4/movie/{id}?token=79ZN2FC-C77M7S5-PKGK9QV-6CSEFZ4")
    Single<TrailerResponse> loadTrailers(@Path("id") int id);

    @GET("v1/review?token=79ZN2FC-C77M7S5-PKGK9QV-6CSEFZ4&page=1&limit=20")
    Single<ReviewResponse> loadReviews(@Query("movieId") int id);
}
