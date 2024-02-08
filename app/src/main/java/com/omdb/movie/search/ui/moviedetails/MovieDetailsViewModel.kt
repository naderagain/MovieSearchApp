package com.omdb.movie.search.ui.moviedetails

import android.app.Application
import android.database.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.omdb.movie.search.R
import com.omdb.movie.search.base.BaseViewModel
import com.omdb.movie.search.models.Movie
import com.omdb.movie.search.repository.IMoviesRepository
import com.omdb.movie.search.state.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    val repository: IMoviesRepository,
    val app: Application
) :
    BaseViewModel(app) {
    var imdbID: String? = null
        set(value) {
            field = value
            field?.let {
                fetchMovieDetails()
            } ?: movie.set(null)

        }
    var movie = ObservableField<Movie?>()
    private fun fetchMovieDetails() {
        viewModelScope.launch {
            repository.fetchMovie(imdbID!!).onEach { state ->
                when (state) {
                    is DataState.Loading -> {
                        isLoading.set(true)
                    }

                    is DataState.Error -> {
                        isLoading.set(false)
                        state.message?.let {
                            showToast(it)
                        }?: showToast(app.getString(R.string.txt_unable_to_fetch_movie_details))
                    }

                    is DataState.Success -> {
                        isLoading.set(false)
                        state.data?.let {
                            movie.set(it)
                        } ?: run {
                            showToast(app.getString(R.string.txt_unable_to_fetch_movie_details))
                        }
                    }

                    else -> {}
                }
            }.launchIn(this)
        }
    }
}