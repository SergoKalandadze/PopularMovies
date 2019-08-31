package com.poal.popular.movies.base

import androidx.lifecycle.ViewModel
import com.poal.popular.movies.injection.component.DaggerViewModelInjector
import com.poal.popular.movies.injection.component.ViewModelInjector
import com.poal.popular.movies.injection.modules.NetworkModule
import com.poal.popular.movies.viewmodels.MovieListViewModel
import com.poal.popular.movies.viewmodels.MovieViewModel

abstract class BaseViewModel: ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector.builder().networkModule(
        NetworkModule
    ).build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is MovieListViewModel -> injector.inject(this)
            is MovieViewModel -> injector.inject(this)
        }
    }
}