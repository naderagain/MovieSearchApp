package com.omdb.movie.search.ui.searchresults.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.omdb.movie.search.R
import com.omdb.movie.search.databinding.ItemLoadstateBinding

class MovieLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<LoadStateViewHolder>() {
    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) =
        holder.bind(loadState)

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder =
        LoadStateViewHolder.create(parent, retry)
}

class LoadStateViewHolder(
    private val binding: ItemLoadstateBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(
    binding.root
) {
    init {
        binding.loadRetryButton.setOnClickListener {
            retry.invoke()
        }
    }

    fun bind(loadState: LoadState) {
        binding.apply {
            loadProgressBar.isVisible = loadState is LoadState.Loading
            retryMessage.isVisible = loadState is LoadState.Error
            loadRetryButton.isVisible = loadState is LoadState.Error
            endOfNewsItemsText.isVisible = loadState.endOfPaginationReached && loadState is LoadState.NotLoading
        }
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): LoadStateViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_loadstate, parent, false)

            val binding = ItemLoadstateBinding.bind(view)
            return LoadStateViewHolder(binding, retry)
        }
    }

}