package com.omdb.movie.search.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.omdb.movie.search.R
import com.omdb.movie.search.base.BaseFragment
import com.omdb.movie.search.databinding.FragmentHomeBinding
import com.omdb.movie.search.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel, MainViewModel>(
    R.layout.fragment_home,
    HomeViewModel::class
) {
    val activityViewModel: MainViewModel by activityViewModels()
    override fun activityViewModel(): MainViewModel = activityViewModel

}