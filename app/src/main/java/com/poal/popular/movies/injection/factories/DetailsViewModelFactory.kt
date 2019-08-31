package com.poal.popular.movies.injection.factories

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.poal.popular.movies.models.database.AppDatabase
import com.poal.popular.movies.ui.views.OnMovieSelected
import com.poal.popular.movies.viewmodels.MovieListViewModel
import com.poal.popular.movies.viewmodels.MovieViewModel
import java.lang.IllegalArgumentException

class DetailsViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MovieViewModel::class.java)){
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "movies").build()
            @Suppress("UNCHECKED_CAST")
            return MovieViewModel(db.moviesDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}