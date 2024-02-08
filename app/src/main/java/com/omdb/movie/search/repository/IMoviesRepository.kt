package com.omdb.movie.search.repository

import androidx.paging.PagingData
import com.omdb.movie.search.models.Movie
import com.omdb.movie.search.models.MoviesResponse
import com.omdb.movie.search.state.DataState
import kotlinx.coroutines.flow.Flow

interface IMoviesRepository {
    suspend fun fetchMovie(imdbID: String): Flow<DataState<Movie>>
    fun searchMovie(searchQuery: String): Flow<PagingData<Movie>>
}