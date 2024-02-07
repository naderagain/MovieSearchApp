package com.omdb.movie.search.ui.searchresults

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.omdb.movie.search.R
import com.omdb.movie.search.base.BaseFragment
import com.omdb.movie.search.databinding.FragmentSearchResultsBinding
import com.omdb.movie.search.ui.MainViewModel
import com.omdb.movie.search.ui.home.HomeFragmentDirections
import com.omdb.movie.search.ui.searchresults.SearchResultsFragmentDirections.Companion.actionOpenMovieDetails
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchResultsFragment : BaseFragment<FragmentSearchResultsBinding,SearchResultsViewModel,MainViewModel>(R.layout.fragment_search_results,SearchResultsViewModel::class) {
    val activityViewModel : MainViewModel by activityViewModels()
    override fun activityViewModel(): MainViewModel = activityViewModel

    private fun openMovieDetails(imdbID:String){
        findNavController().navigate(actionOpenMovieDetails(imdbID))
    }
}