package com.da62.ui

import com.da62.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment() {

    private val viewModel by viewModel<MainViewModel>()
}