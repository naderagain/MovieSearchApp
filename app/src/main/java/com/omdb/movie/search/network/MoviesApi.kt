package com.omdb.movie.search.network

import com.omdb.movie.search.models.Movie
import com.omdb.movie.search.models.MoviesResponse
import com.omdb.movie.search.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
   @GET("/")
   suspend fun searchMovie(
        @Query("s")
        searchQuery:String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apikey")
        apiKey:String=API_KEY): Response<MoviesResponse>
    @GET("/")
    suspend fun fetchMovie(
        @Query("i")
        imdbID: String,
        @Query("apikey")
        apiKey:String=API_KEY): Response<Movie>
}