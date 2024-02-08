package com.omdb.movie.search.extras

import android.text.TextWatcher
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputEditText

@BindingAdapter("app:textWatcher")
fun TextInputEditText.addTextWatcher(watcher: TextWatcher?)
{
    addTextChangedListener(watcher)
}

@BindingAdapter("app:loadImage")
fun ImageView.loadImage(imageUrl: String) {
    Glide.with(this).load(imageUrl).into(this);
}
