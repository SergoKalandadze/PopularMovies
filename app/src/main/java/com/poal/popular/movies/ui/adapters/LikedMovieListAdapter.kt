package com.poal.popular.movies.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.poal.popular.movies.R
import com.poal.popular.movies.databinding.LikedMovieItemBinding

import com.poal.popular.movies.models.Movie
import com.poal.popular.movies.viewmodels.MovieViewModel

class LikedMovieListAdapter(): RecyclerView.Adapter<LikedMovieListAdapter.ViewHolder>() {

    private lateinit var  moviesList:List<Movie>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikedMovieListAdapter.ViewHolder {
        val binding: LikedMovieItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.liked_movie_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LikedMovieListAdapter.ViewHolder, position: Int) {
        holder.bind(moviesList[position])
    }

    override fun getItemCount(): Int {
        return if(::moviesList.isInitialized) moviesList.size else 0
    }

    fun updateMovieList(moviesList:List<Movie>){
        this.moviesList = moviesList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: LikedMovieItemBinding): RecyclerView.ViewHolder(binding.root) {
        private val viewModel = MovieViewModel(null)

        fun bind(movie: Movie) {
            viewModel.bind(movie)
            binding.viewModel = viewModel
        }
    }
}