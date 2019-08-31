package com.poal.popular.movies.ui.views


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.poal.popular.movies.R

import com.poal.popular.movies.databinding.MovieListFragmentBinding
import com.poal.popular.movies.injection.factories.ListViewModelFactory
import com.poal.popular.movies.viewmodels.MovieListViewModel


class MovieListFragment : Fragment() {

    private lateinit var binding: MovieListFragmentBinding
    private lateinit var viewModel: MovieListViewModel
    private var errorSnackbar: Snackbar? = null


    companion object {
        fun newInstance() = MovieListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.movie_list_fragment, container, false)

        binding.movieList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProviders.of(this, ListViewModelFactory(activity as AppCompatActivity,
            activity as MoviesActivity, activity as MoviesActivity)).get(MovieListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer {
                errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun showError(@StringRes errorMessage:Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()

    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }


}


