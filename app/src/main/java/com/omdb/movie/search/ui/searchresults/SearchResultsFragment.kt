package com.omdb.movie.search.ui.searchresults

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.DividerItemDecoration
import com.omdb.movie.search.R
import com.omdb.movie.search.base.BaseFragment
import com.omdb.movie.search.databinding.FragmentSearchResultsBinding
import com.omdb.movie.search.models.Movie
import com.omdb.movie.search.ui.MainViewModel
import com.omdb.movie.search.ui.home.HomeFragmentDirections
import com.omdb.movie.search.ui.searchresults.SearchResultsFragmentDirections.Companion.actionOpenMovieDetails
import com.omdb.movie.search.ui.searchresults.adapters.MovieLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SearchResultsFragment : BaseFragment<FragmentSearchResultsBinding,SearchResultsViewModel,MainViewModel>(R.layout.fragment_search_results,SearchResultsViewModel::class) {
    val activityViewModel : MainViewModel by activityViewModels()
    val moviesSearchAdapter = MoviesSearchAdapter()
    override fun activityViewModel(): MainViewModel = activityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            viewModel.searchMovieName=SearchResultsFragmentArgs.fromBundle(it).movieName
        }
        moviesSearchAdapter.movieSearchItemClick=::onMovieItemClick
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbarSearch.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.btnRetry.setOnClickListener { moviesSearchAdapter.retry() }
        binding.rvSearchResults.adapter=moviesSearchAdapter.withLoadStateHeaderAndFooter(header = MovieLoadStateAdapter{moviesSearchAdapter.retry()}, footer = MovieLoadStateAdapter{moviesSearchAdapter.retry()})
        binding.rvSearchResults.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
            setDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.drawable_divider)!!)
        })
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.moviesSearchFlowData?.collectLatest{value: PagingData<Movie> ->
                    moviesSearchAdapter.submitData(value)
                    moviesSearchAdapter.retry()
                }
            }
        }

        moviesSearchAdapter.addLoadStateListener {loadStates ->
            with(binding){
                txtNoResultFound.isVisible = (loadStates.source.refresh is LoadState.NotLoading && moviesSearchAdapter.itemCount == 0)
                progressBar.isVisible=loadStates.source.refresh is LoadState.Loading
                rvSearchResults.isVisible=loadStates.source.refresh is LoadState.NotLoading
                btnRetry.isVisible=loadStates.source.refresh is LoadState.Error
            }
        }
    }

    private fun onMovieItemClick(position:Int,movie: Movie){
        movie.imdbID?.let {
            openMovieDetails(it)
        }

    }
    private fun openMovieDetails(imdbID:String){
        findNavController().navigate(actionOpenMovieDetails(imdbID))
    }
}