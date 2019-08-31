package com.poal.popular.movies.ui.views

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.poal.popular.movies.R
import com.poal.popular.movies.databinding.MovieDetailFragmentBinding
import com.poal.popular.movies.injection.factories.DetailsViewModelFactory
import com.poal.popular.movies.injection.factories.ListViewModelFactory
import com.poal.popular.movies.viewmodels.MovieListViewModel
import com.poal.popular.movies.viewmodels.MovieViewModel

private const val MOVIE_ID = "movieId"

class MovieDetailFragment : Fragment() {

    private lateinit var binding: MovieDetailFragmentBinding
    private lateinit var viewModel: MovieViewModel

    private var movieId: Int? = null

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieId = it.getInt(MOVIE_ID)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_detail_fragment, container, false)

        viewModel = ViewModelProviders.of(this, DetailsViewModelFactory(activity as AppCompatActivity)).get(MovieViewModel::class.java)

        binding.viewModel = viewModel

        viewModel.getMovie(movieId!!)

        viewModel.liked.observe(this, Observer {
            if(viewModel.liked.value!!) {
                binding.likeButton.visibility = View.INVISIBLE
            } else {
                binding.likeButton.visibility = View.VISIBLE
            }
        })

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        @JvmStatic
        fun newInstance(movieId: Int) =
            MovieDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(MOVIE_ID, movieId)
                }
            }
    }

}
