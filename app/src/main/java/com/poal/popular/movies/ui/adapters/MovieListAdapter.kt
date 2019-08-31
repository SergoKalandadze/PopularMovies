package com.poal.popular.movies.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.poal.popular.movies.R
import com.poal.popular.movies.databinding.MovieItemBinding
import com.poal.popular.movies.models.Movie
import com.poal.popular.movies.ui.views.OnMovieSelected
import com.poal.popular.movies.viewmodels.MovieViewModel

class MovieListAdapter(private val listener: OnMovieSelected): RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    private lateinit var  moviesList:List<Movie>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListAdapter.ViewHolder {
        val binding: MovieItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.movie_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieListAdapter.ViewHolder, position: Int) {
        holder.bind(moviesList[position])

        holder.itemView.setOnClickListener { listener.onMovieSelected(moviesList[position].movieId) }
    }

    override fun getItemCount(): Int {
        return if(::moviesList.isInitialized) moviesList.size else 0
    }

    fun updateMovieList(moviesList:List<Movie>){
        this.moviesList = moviesList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: MovieItemBinding):RecyclerView.ViewHolder(binding.root) {
        private val viewModel = MovieViewModel(null)

        fun bind(movie:Movie) {
            viewModel.bind(movie)
            binding.viewModel = viewModel
        }
    }
}