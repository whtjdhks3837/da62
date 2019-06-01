package com.da62.ui.splash

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.da62.R
import com.da62.databinding.ActivitySplashBinding
import com.da62.ui.MainActivity
import com.da62.ui.base.BaseActivity
import org.jetbrains.anko.intentFor
import org.koin.androidx.viewmodel.ext.android.viewModel


class SplashActivity : BaseActivity() {

    private val viewModel by viewModel<SplashViewModel>()
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.openToMain.observe(this, Observer {
            startActivity(intentFor<MainActivity>()).apply {
                finish()
            }
        })
    }

}