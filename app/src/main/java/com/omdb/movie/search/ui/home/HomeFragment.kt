package com.omdb.movie.search.ui.home


import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.omdb.movie.search.R
import com.omdb.movie.search.base.BaseFragment
import com.omdb.movie.search.databinding.FragmentHomeBinding
import com.omdb.movie.search.ui.MainViewModel
import com.omdb.movie.search.ui.home.HomeFragmentDirections.Companion.actionSearchMovie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel, MainViewModel>(
    R.layout.fragment_home,
    HomeViewModel::class
) {
    val activityViewModel: MainViewModel by activityViewModels()
    override fun activityViewModel(): MainViewModel = activityViewModel

    private fun searchForMovie(movieName:String){
        findNavController().navigate(actionSearchMovie(movieName))
    }
}