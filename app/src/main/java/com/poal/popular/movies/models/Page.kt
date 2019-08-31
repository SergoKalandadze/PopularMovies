package com.poal.popular.movies.models

import com.squareup.moshi.Json

data class Page(
    @field:Json(name = "results")val results: List<Movie>)