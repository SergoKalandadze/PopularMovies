package com.poal.popular.movies.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.poal.popular.movies.base.BaseViewModel
import com.poal.popular.movies.models.Movie
import com.poal.popular.movies.models.MoviesDao
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MovieViewModel(private val moviesDao: MoviesDao?):BaseViewModel() {

    private var movieId:Int = 0
    private val movieTitle = MutableLiveData<String>()
    private val posterUrl = MutableLiveData<String>()
    private val backUrl = MutableLiveData<String>()
    private val description = MutableLiveData<String>()
    private val rating = MutableLiveData<String>()
    private val date = MutableLiveData<String>()
    val liked = MutableLiveData<Boolean>()

    private lateinit var subscription: Disposable

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    fun bind(movie: Movie) {
        movieId = movie.movieId
        movieTitle.value = movie.title
        posterUrl.value = movie.posterUrl
        backUrl.value = movie.backUrl
        description.value = movie.description
        rating.value = movie.rating.toString()
        date.value = movie.date
        liked.value = movie.liked
    }

    fun getMovieTitle():MutableLiveData<String> {
        return movieTitle
    }

    fun getPosterUrl():MutableLiveData<String> {
        return posterUrl
    }

    fun getBackdropUrl():MutableLiveData<String> {
        return backUrl
    }

    fun getDescription():MutableLiveData<String> {
        return description
    }

    fun getRating():MutableLiveData<String> {
        return rating
    }

    fun getReleaseDate():MutableLiveData<String> {
        return date
    }

    fun isLiked():MutableLiveData<Boolean> {
        return liked
    }

    fun onClickLike() {
        liked.value = true

        subscription = Observable.fromCallable { moviesDao?.updateLiked(movieId, liked.value!!) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun getMovie(movieId: Int) {

        subscription = Observable.fromCallable { moviesDao?.single(movieId) }
                .concatMap {
                    dbMovie -> Observable.just(dbMovie)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> onRetrieveMovieSuccess(result) },
                { onRetrieveMovieError() }
            )

    }

    private fun onRetrieveMovieSuccess(movie: Movie) {

        bind(movie)

    }

    private fun onRetrieveMovieError() {
        Log.e(javaClass.name, "Error getting movie from db")
    }
}


