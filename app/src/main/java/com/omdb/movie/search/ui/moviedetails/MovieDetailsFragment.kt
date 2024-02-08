package com.omdb.movie.search.ui.moviedetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.omdb.movie.search.R
import com.omdb.movie.search.base.BaseFragment
import com.omdb.movie.search.databinding.FragmentMovieDetailsBinding
import com.omdb.movie.search.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding,MovieDetailsViewModel,MainViewModel>(R.layout.fragment_movie_details,MovieDetailsViewModel::class,MainViewModel::class) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}