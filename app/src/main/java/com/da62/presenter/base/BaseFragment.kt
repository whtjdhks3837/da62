package com.da62.presenter.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import org.jetbrains.anko.AnkoLogger

abstract class BaseFragment<T : ViewDataBinding> : Fragment(), AnkoLogger {

    lateinit var binding: T

    abstract val resourceId: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        DataBindingUtil.inflate<T>(
            LayoutInflater.from(context), resourceId, container, false
        ).apply {
            binding = this
            binding.lifecycleOwner = this@BaseFragment
        }.root
}