package com.omdb.movie.search.network

import com.omdb.movie.search.models.Movie
import com.omdb.movie.search.models.MoviesResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor( val moviesApi: MoviesApi) :ApiHelper{
    override suspend fun searchMovie(
        queryString: String,
        pageNumber: Int
    ): MoviesResponse = moviesApi.searchMovie(searchQuery = queryString, pageNumber = pageNumber)

    override suspend fun fetchMovie(imdbID: String): Response<Movie>  = moviesApi.fetchMovie(imdbID = imdbID)
}