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

abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel, AVM : BaseViewModel>(
    @LayoutRes val layout: Int,
    private val viewModelClass: KClass<VM>, private val activityViewModelClass: KClass<AVM>
) : Fragment() {
    open lateinit var binding: VB
    open lateinit var viewModel: VM
    open lateinit var activityViewModel: AVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[viewModelClass.java]
        activityViewModel = ViewModelProvider(this)[activityViewModelClass.java]
        viewModel.hideKeyboardEvent.observe(this) {
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
        binding.setVariable(BR.activityViewModel, activityViewModel)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.toastMessage.observe(viewLifecycleOwner) {
            activityViewModel.toastMessage.value = it
        }
    }

    inline fun <reified specific : ViewDataBinding> getSpecificBinding() = binding as? specific
}