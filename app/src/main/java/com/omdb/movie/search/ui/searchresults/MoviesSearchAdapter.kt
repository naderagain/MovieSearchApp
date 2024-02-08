package com.omdb.movie.search.ui.searchresults

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.omdb.movie.search.databinding.ItemMovieSearchBinding
import com.omdb.movie.search.models.Movie

class MoviesSearchAdapter:PagingDataAdapter<Movie, MoviesSearchAdapter.MovieSearchViewHolder>(MoviesSearchDiffCallback) {

    var movieSearchItemClick : ((position:Int, movie:Movie, movieName: TextView, imageView:ImageView)->Unit)?=null
    override fun onBindViewHolder(holder: MovieSearchViewHolder, position: Int) {
        getItem(position)?.let {movie->
            holder.binding.movie=movie
            holder.binding.layoutMovieSearchItem.setOnClickListener {
                ViewCompat.setTransitionName(holder.binding.imgMoviePoster,movie.imdbID!!)
                movieSearchItemClick?.invoke(position,movie,holder.binding.textViewMovieName,holder.binding.imgMoviePoster)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSearchViewHolder =
        MovieSearchViewHolder(ItemMovieSearchBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    class MovieSearchViewHolder(val binding: ItemMovieSearchBinding) : ViewHolder(binding.root)
    object MoviesSearchDiffCallback  : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.imdbID == newItem.imdbID
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}