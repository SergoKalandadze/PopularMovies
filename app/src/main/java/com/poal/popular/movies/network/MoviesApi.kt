package com.poal.popular.movies.network

import com.poal.popular.movies.models.Page
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Call

import retrofit2.http.GET

interface MoviesApi {

    @GET("/3/movie/popular?language=en-US&page=1")
    fun getMovies(): Observable<Page>
}