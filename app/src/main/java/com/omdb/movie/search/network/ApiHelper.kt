package com.omdb.movie.search.network

import com.omdb.movie.search.models.Movie
import com.omdb.movie.search.models.MoviesResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun searchMovie(queryString: String, pageNumber: Int): Response<MoviesResponse>

    suspend fun fetchMovie(imdbID: String): Response<Movie>
}