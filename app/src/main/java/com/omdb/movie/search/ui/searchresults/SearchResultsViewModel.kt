package com.omdb.movie.search.ui.searchresults

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.omdb.movie.search.base.BaseViewModel
import com.omdb.movie.search.models.Movie
import com.omdb.movie.search.repository.IMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SearchResultsViewModel @Inject constructor(
    val repository: IMoviesRepository,
    application: Application
) :
    BaseViewModel(application) {
    var searchMovieName = ""
        set(value) {
            field = value
            searchMovieName()
        }
    var moviesSearchFlowData: Flow<PagingData<Movie>>? = null
    private fun searchMovieName() {
        moviesSearchFlowData = repository.searchMovie(searchMovieName).cachedIn(viewModelScope)
    }
}