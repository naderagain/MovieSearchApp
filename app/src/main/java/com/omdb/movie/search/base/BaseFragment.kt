package com.omdb.movie.search.base

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.omdb.movie.search.BR
import com.omdb.movie.search.extras.hideKeyboard
import kotlin.reflect.KClass

abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel,AVM:BaseViewModel>(
    @LayoutRes val layout: Int,
    private val viewModelClass: KClass<VM>
) : Fragment() {
    open lateinit var binding: VB
    open lateinit var viewModel: VM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[viewModelClass.java]
        viewModel.hideKeyboardEvent.observe(this){
            hideKeyboard()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layout, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.setVariable(BR.viewModel, viewModel)
        binding.setVariable(BR.activityViewModel, activityViewModel())
        return binding.root
    }
    abstract fun activityViewModel(): AVM
    inline fun <reified specific : ViewDataBinding> getSpecificBinding() = binding as? specific
}