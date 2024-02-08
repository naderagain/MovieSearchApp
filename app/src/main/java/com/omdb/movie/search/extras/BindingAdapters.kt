package com.omdb.movie.search.extras

import android.text.TextWatcher
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText

@BindingAdapter("app:textWatcher")
fun TextInputEditText.addTextWatcher(watcher: TextWatcher?)
{
    addTextChangedListener(watcher)
}
