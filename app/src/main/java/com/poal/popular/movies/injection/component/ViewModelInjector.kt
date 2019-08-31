package com.poal.popular.movies.injection.component

import com.poal.popular.movies.injection.modules.NetworkModule
import com.poal.popular.movies.viewmodels.MovieListViewModel
import com.poal.popular.movies.viewmodels.MovieViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    fun inject(movieListViewModel: MovieListViewModel)

    fun inject(movieViewModel: MovieViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}