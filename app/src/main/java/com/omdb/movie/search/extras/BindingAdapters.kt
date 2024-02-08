package com.omdb.movie.search.extras

import android.text.TextWatcher
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputEditText

@BindingAdapter("app:textWatcher")
fun TextInputEditText.addTextWatcher(watcher: TextWatcher?) {
    addTextChangedListener(watcher)
}

@BindingAdapter("app:loadImage")
fun ImageView.loadImage(imageUrl: String) {
    Glide.with(this).load(imageUrl).into(this);
}

@BindingAdapter("app:updateVisibility")
fun View.updateVisibility(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE
    else View.GONE
}