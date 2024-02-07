package com.omdb.movie.search.base

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import com.omdb.movie.search.extras.SingleLiveEvent

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    val isLoading = ObservableBoolean(false)
    val onGoBackEvent = SingleLiveEvent<Void?>()
}