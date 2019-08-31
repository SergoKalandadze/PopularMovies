package com.poal.popular.movies.injection.factories

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.poal.popular.movies.models.database.AppDatabase
import com.poal.popular.movies.viewmodels.LikedMovieListViewModel
import java.lang.IllegalArgumentException

class LikedListViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LikedMovieListViewModel::class.java)){
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "movies").build()
            @Suppress("UNCHECKED_CAST")
            return LikedMovieListViewModel(db.moviesDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}