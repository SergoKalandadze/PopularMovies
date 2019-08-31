package com.poal.popular.movies.models

import androidx.room.ColumnInfo
import androidx.room.Entity

import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class Movie(
    @field: PrimaryKey
    @field:Json(name = "id")val movieId: Int,
    @field:Json(name = "original_title")val title: String,
    @field:Json(name = "poster_path")val posterUrl: String,
    @field:Json(name = "release_date")val date: String,
    @field:Json(name = "vote_average")val rating: Float,
    @field:Json(name = "overview")val description: String,
    @field:Json(name = "backdrop_path")val backUrl: String,
    val liked:Boolean = false)
