package com.poal.popular.movies.viewmodels

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.poal.popular.movies.R
import com.poal.popular.movies.base.BaseViewModel
import com.poal.popular.movies.models.Movie
import com.poal.popular.movies.models.MoviesDao
import com.poal.popular.movies.network.MoviesApi
import com.poal.popular.movies.ui.adapters.MovieListAdapter
import com.poal.popular.movies.ui.views.OnMovieSelected
import com.poal.popular.movies.ui.views.liked.OnShowLiked
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieListViewModel(private val moviesDao: MoviesDao,
                         private val onMovieSelected: OnMovieSelected,
                         private val onShowLiked: OnShowLiked): BaseViewModel() {
    @Inject
    lateinit var moviesApi: MoviesApi
    val movieListAdapter: MovieListAdapter = MovieListAdapter(onMovieSelected)

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadMovies() }

    private lateinit var subscription: Disposable

    init{
        loadMovies()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadMovies() {
        subscription = Observable.fromCallable { moviesDao.all }
            .concatMap {
                    dbMovieList ->
                if(dbMovieList.isEmpty()){
                    moviesApi.getMovies().concatMap {
                            apiMoviesList -> moviesDao.insertAll(*apiMoviesList.results.toTypedArray())
                        Observable.just(apiMoviesList.results)
                    }
                } else {
                    Observable.just(dbMovieList)
                }

            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveMovieListStart() }
            .doOnTerminate { onRetrieveMovieListFinish() }
            .subscribe(
                { result -> onRetrieveMovieListSuccess(result) },
                { onRetrieveMovieListError() }
            )

    }

    private fun onRetrieveMovieListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveMovieListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveMovieListSuccess(movieList:List<Movie>) {
        movieListAdapter.updateMovieList(movieList)
    }

    private fun onRetrieveMovieListError() {
        errorMessage.value = R.string.movies_load_error
    }

    fun onShowLikedClick() {

        onShowLiked.onShowLikedClick()

    }

}