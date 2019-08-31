package com.poal.popular.movies.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface MoviesDao {
    @get:Query("SELECT * FROM movie")
    val all: List<Movie>

    @Query("SELECT * FROM movie WHERE movieId == :id LIMIT 1")
    fun single(id: Int): Movie

    @get:Query("SELECT * FROM movie WHERE liked > 0")
    val liked: List<Movie>

    @Query("UPDATE movie SET liked=:isLiked WHERE movieId == :id")
    fun updateLiked(id: Int, isLiked:Boolean)

    @Insert
    fun insertAll(vararg movies: Movie)
}