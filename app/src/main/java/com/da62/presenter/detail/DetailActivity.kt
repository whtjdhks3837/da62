package com.da62.presenter.detail

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
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

        binding.detailHorizontalRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(this@DetailActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = DetailInfoAdapter(this@DetailActivity)
            addItemDecoration(
                DetailItemDecoration(
                    dp2px(this@DetailActivity, 24f),
                    dp2px(this@DetailActivity, 16f)
                )
            )
        }

        binding.detailGalleryRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(this@DetailActivity, LinearLayoutManager.HORIZONTAL, false)
        }

        val infoSnapHelper = LinearSnapHelper()
        infoSnapHelper.attachToRecyclerView(binding.detailHorizontalRecyclerView)

        viewModel.clickToBack.observe(this, Observer {
           supportFinishAfterTransition()
        })

    }

    // transition complete
    override fun onEnterAnimationComplete() {
        super.onEnterAnimationComplete()

    }
}