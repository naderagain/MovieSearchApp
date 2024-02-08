package com.omdb.movie.search.ui.home

import android.app.Application
import android.text.Editable
import android.text.TextWatcher
import androidx.core.text.trimmedLength
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import com.omdb.movie.search.base.BaseViewModel
import com.omdb.movie.search.extras.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(application: Application) : BaseViewModel(application) {
    var movieNameSearch = ObservableField("")
    var searchMovieNameEvent= SingleLiveEvent<String>()
    var movieSearchTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            movieNameSearch.set("" + s)
        }

        override fun afterTextChanged(s: Editable) {}
    }

    fun onSearchMovie(){
        hideKeyboard()
        movieNameSearch.get()?.let {searchText->
            if (searchText.trimmedLength()>0){
                searchMovieNameEvent.value=searchText
            }
        }
    }
}