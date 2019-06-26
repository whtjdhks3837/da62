package com.da62.presenter.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import org.jetbrains.anko.AnkoLogger

abstract class BaseFragment<T : ViewDataBinding> : Fragment(), AnkoLogger {

    lateinit var mBinding: T

    abstract val mResourceId: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        DataBindingUtil.inflate<T>(
            LayoutInflater.from(context), mResourceId, container, false
        ).apply {
            mBinding = this
            mBinding.lifecycleOwner = this@BaseFragment
        }.root
}