package com.frolaan.movies;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.frolaan.movies.api.ApiFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailViewModel extends AndroidViewModel {

    private final MutableLiveData<List<Trailer>> trailers = new MutableLiveData<>();
    private final MutableLiveData<List<Review>> reviews = new MutableLiveData<>();
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private static final String TAG = "MovieDetailViewModel";
    public LiveData<List<Trailer>> getTrailers() {
        return trailers;
    }
    public LiveData<List<Review>> getReviews() {
        return reviews;
    }
    private final MovieDao movieDao;



    public MovieDetailViewModel(@NonNull Application application) {
        super(application);
        movieDao = MovieDatabase.getInstance(application).movieDao();
    }

    public LiveData<Movie> getFavouriteMovie(int movieId) {
        return movieDao.getFavouriteMovie(movieId);
    }

    public void insertMovie(Movie movie) {
        Disposable disposable = movieDao.insertMovie(movie)
                .subscribeOn(Schedulers.io())
                .subscribe();
        compositeDisposable.add(disposable);
    }

    public void removeMovie(int movieId) {
        Disposable disposable = movieDao.removeMovie(movieId)
                .subscribeOn(Schedulers.io())
                .subscribe();
        compositeDisposable.add(disposable);
    }

    public void loadTrailers(int id) {
        Disposable disposable = ApiFactory.apiService.loadTrailers(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<TrailerResponse, List<Trailer>>() {
                    @Override
                    public List<Trailer> apply(TrailerResponse trailerResponse) throws Throwable {
                        return trailerResponse.getTrailersList().getTrailers();
                    }
                })
                .subscribe(new Consumer<List<Trailer>>() {
                    @Override
                    public void accept(List<Trailer> trailerList) throws Throwable {

                        Set<Trailer> trailerHashSet = new HashSet<>();
                        for(int i = 0; i<trailerList.size(); i++) {
                            trailerHashSet.add(trailerList.get(i));
                            Log.d(TAG, "trailer #" + i + " was added");
                        }
                        Log.d(TAG, trailerHashSet.toString());
                        List<Trailer> trailers1 = new ArrayList<>(trailerHashSet);
                        trailers.setValue(trailers1);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d(TAG, throwable.toString());
                    }
                });
        compositeDisposable.add(disposable);
    }
//         - приложение крашится
//    public void loadReviews(int id) {
//        Disposable disposable = ApiFactory.apiService.loadReviews(id)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .map(new Function<ReviewResponse, List<Review>>() {
//                    @Override
//                    public List<Review> apply(ReviewResponse reviewResponse) throws Throwable {
//                        return reviewResponse.getReviews();
//                    }
//                })
//                .subscribe(new Consumer<List<Review>>() {
//                    @Override
//                    public void accept(List<Review> reviewList) throws Throwable {
//                        reviews.setValue(reviewList);
//                        Log.d(TAG, reviews.toString());
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Throwable {
//                        Log.d(TAG, throwable.toString());
//                    }
//                });
//        compositeDisposable.add(disposable);
//    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
