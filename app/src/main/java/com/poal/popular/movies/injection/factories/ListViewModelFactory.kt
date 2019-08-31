package com.poal.popular.movies.injection.factories

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.poal.popular.movies.models.database.AppDatabase
import com.poal.popular.movies.ui.views.OnMovieSelected
import com.poal.popular.movies.ui.views.liked.OnShowLiked
import com.poal.popular.movies.viewmodels.MovieListViewModel
import java.lang.IllegalArgumentException

class ListViewModelFactory(private val activity: AppCompatActivity,
                           private val onMovieSelected: OnMovieSelected,
                           private val onShowLiked: OnShowLiked): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MovieListViewModel::class.java)){
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "movies").build()
            @Suppress("UNCHECKED_CAST")
            return MovieListViewModel(db.moviesDao(), onMovieSelected, onShowLiked) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}