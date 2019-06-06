package com.da62.presenter.detail

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.da62.R
import com.da62.databinding.ActivityDetailBinding
import com.da62.presenter.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : BaseActivity() {

    private val viewModel by viewModel<DetailViewModel>()
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}