package com.da62.presenter.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.da62.R
import com.da62.databinding.ActivityMainBinding
import com.da62.presenter.base.BaseActivity
import com.da62.presenter.detail.DetailActivity
import org.jetbrains.anko.intentFor
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val viewModel by viewModel<MainViewModel>()
    private lateinit var binding: ActivityMainBinding

    private val snapHelper by lazy { LinearSnapHelper() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.mainRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = MainAdapter(viewModel, this@MainActivity)
        }

        snapHelper.attachToRecyclerView(binding.mainRecyclerView)

        viewModel.clickToItem.observe(this, Observer {
            startActivity(intentFor<DetailActivity>())
        })
    }
}
