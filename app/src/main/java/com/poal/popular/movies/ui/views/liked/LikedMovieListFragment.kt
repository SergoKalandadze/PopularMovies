package com.poal.popular.movies.ui.views.liked

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.poal.popular.movies.R
import com.poal.popular.movies.databinding.LikedMovieListFragmentBinding
import com.poal.popular.movies.injection.factories.LikedListViewModelFactory
import com.poal.popular.movies.ui.views.MovieListFragment
import com.poal.popular.movies.viewmodels.LikedMovieListViewModel

class LikedMovieListFragment : Fragment() {


    private lateinit var binding: LikedMovieListFragmentBinding
    private lateinit var viewModel: LikedMovieListViewModel

    companion object {
        fun newInstance() = LikedMovieListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.liked_movie_list_fragment, container, false)

        binding.movieList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProviders.of(
            this, LikedListViewModelFactory(activity as AppCompatActivity)
        ).get(LikedMovieListViewModel::class.java)

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}

