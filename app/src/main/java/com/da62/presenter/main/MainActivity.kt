package com.da62.presenter.main

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.da62.R
import com.da62.databinding.ActivityMainBinding
import com.da62.model.Plant
import com.da62.presenter.base.BaseActivity
import com.da62.presenter.detail.DetailActivity
import com.da62.presenter.write.WriteActivity
import com.da62.util.dp2px
import org.jetbrains.anko.intentFor
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(), MainEventListener {

    private val viewModel by viewModel<MainViewModel>()
    private lateinit var binding: ActivityMainBinding

    private val snapHelper by lazy { LinearSnapHelper() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.mainRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = MainAdapter(this@MainActivity, this@MainActivity)
            addItemDecoration(
                MainItemDecoration(
                    dp2px(this@MainActivity, 24f),
                    dp2px(this@MainActivity, 16f)
                )
            )
        }

        snapHelper.attachToRecyclerView(binding.mainRecyclerView)

        viewModel.clickToAdd.observe(this, Observer {
            startActivity(intentFor<WriteActivity>())
        })
    }

    override fun onItemClick(view: View, position: Int, plant: Plant) {
        val imageView = view.findViewById<View>(R.id.item_main_image)
        val pair = Pair(imageView, imageView.transitionName)
        val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pair)
        startActivity(intentFor<DetailActivity>(), optionsCompat.toBundle())
    }
}
interface MainEventListener {

    fun onItemClick(view: View, position: Int, plant: Plant)
}