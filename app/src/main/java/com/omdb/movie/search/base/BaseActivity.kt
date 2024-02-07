package com.omdb.movie.search.base

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.omdb.movie.search.BR
import kotlin.reflect.KClass

abstract class BaseActivity<VB : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes val layout: Int,
    private val viewModelClass: KClass<VM>
) : AppCompatActivity(){
    lateinit var binding : VB
    open lateinit var viewModel: VM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[viewModelClass.java]
        binding = DataBindingUtil.setContentView(this, layout)
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, viewModel)
    }
}