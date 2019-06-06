package com.da62.presenter.write

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.da62.R
import com.da62.databinding.ActivityWriteBinding
import com.da62.presenter.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class WriteActivity : BaseActivity() {

    private val viewModel by viewModel<WriteViewModel>()
    private lateinit var binding: ActivityWriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_write)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

}