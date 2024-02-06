package com.omdb.movie.search.models

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.omdb.movie.search.network.ApiHelper
import com.omdb.movie.search.utils.Constants
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

const val STARTING_PAGE_INDEX = 1

class MoviesSearchAdapter @Inject constructor(
    val remoteDataSource: ApiHelper,
    val searchQuery: String
) :
    PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val searchResponse = remoteDataSource.searchMovie(searchQuery, position)
            LoadResult.Page(
                data = searchResponse.Search,
                prevKey = if (position > 1) position - 1 else null,
                nextKey = if (searchResponse.Response == "True") position + 1 else null
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}