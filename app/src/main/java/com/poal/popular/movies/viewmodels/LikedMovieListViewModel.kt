package com.poal.popular.movies.viewmodels

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.poal.popular.movies.models.Movie
import com.poal.popular.movies.models.MoviesDao
import com.poal.popular.movies.ui.adapters.LikedMovieListAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class LikedMovieListViewModel(private val moviesDao: MoviesDao): ViewModel() {

    val likedMovieListAdapter: LikedMovieListAdapter = LikedMovieListAdapter()

    val noLikedMoviesVisibility: MutableLiveData<Int> = MutableLiveData()

    private lateinit var subscription: Disposable

    init{
        getMovies()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun getMovies() {
        subscription = Observable.fromCallable { moviesDao.liked }
            .concatMap {
                    dbMovieList -> Observable.just(dbMovieList)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> onRetrieveMovieListSuccess(result) },
                { onRetrieveMovieListError() }
            )

    }

    private fun onRetrieveMovieListSuccess(movieList:List<Movie>) {
        if(movieList.isEmpty()) {
            noLikedMoviesVisibility.value = View.VISIBLE
            return
        }
        noLikedMoviesVisibility.value = View.GONE
        likedMovieListAdapter.updateMovieList(movieList)
    }

    private fun onRetrieveMovieListError() {
        Log.e(javaClass.name, "Error getting movie list from db")
    }


}