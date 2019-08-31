package com.poal.popular.movies.models.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.poal.popular.movies.models.Movie
import com.poal.popular.movies.models.MoviesDao

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}