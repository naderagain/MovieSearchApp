package com.omdb.movie.search.ui.searchresults

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.omdb.movie.search.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchResultsViewModel @Inject constructor(application: Application) : BaseViewModel(application) {
}