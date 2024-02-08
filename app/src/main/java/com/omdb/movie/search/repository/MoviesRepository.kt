package com.omdb.movie.search.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.omdb.movie.search.models.Movie
import com.omdb.movie.search.models.MoviesSearchDataSource
import com.omdb.movie.search.network.ApiHelper
import com.omdb.movie.search.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val remoteDataSource: ApiHelper) :
    IMoviesRepository {
    override suspend fun fetchMovie(imdbID: String): Flow<DataState<Movie>> = flow {
        emit(DataState.Loading())
        try {
            val response = remoteDataSource.fetchMovie(imdbID = imdbID)
            val result = response.body()
            if (response.isSuccessful && result != null)
                emit(DataState.Success(result))
            else throw Exception("Unable to fetch movie!")
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error("Unable to fetch movie!"))
        }
    }

    override fun searchMovie(
        searchQuery: String
    ): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 10, maxSize = 30, enablePlaceholders = false),
            pagingSourceFactory = { MoviesSearchDataSource(remoteDataSource, searchQuery) }
        ).flow
    }
}