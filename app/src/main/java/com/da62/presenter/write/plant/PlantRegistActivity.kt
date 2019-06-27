package com.da62.presenter.write.plant

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.viewpager.widget.PagerAdapter
import com.da62.R
import com.da62.databinding.ActivityPlantRegistBinding
import com.da62.presenter.base.BaseActivity
import com.da62.presenter.write.plant.fragment.PlantInfoFragment
import com.da62.presenter.write.plant.fragment.PlantSpeciesFragment
import com.da62.presenter.write.plant.fragment.PlantWaterSettingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlantRegistActivity : BaseActivity() {

    private val viewModel by viewModel<PlantRegistViewModel>()

    private lateinit var binding: ActivityPlantRegistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_plant_regist)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        init()
        nextObserve()
    }

    private fun init() {
        initViewPager(
            createViewPagerAdapter(
                listOf(
                    PlantSpeciesFragment.createInstance(),
                    PlantInfoFragment.createInstance(),
                    PlantWaterSettingFragment.createInstance()
                )
            )
        )
    }

    private fun initViewPager(pagerAdapter: PagerAdapter) = binding.viewPager.apply {
        adapter = pagerAdapter
        currentItem = 0
        beginFakeDrag()
    }

    private fun createViewPagerAdapter(pages: List<Fragment>) =
        PlantRegistViewPagerAdapter(supportFragmentManager)
            .apply {
                setPage(pages)
            }

    private fun nextObserve() = viewModel.next.observe(this, Observer {
        binding.viewPager.apply {
            if (currentItem == childCount) {
                //Do Something...
            } else {
                currentItem += 1
            }
        }
    })
}

class PlantRegistViewPagerAdapter(private val mFragmentManager: FragmentManager) :
    FragmentPagerAdapter(mFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val pages = mutableListOf<Fragment>()

    override fun getItem(position: Int) = pages[position]

    override fun getCount() = pages.size

    fun setPage(pages: List<Fragment>) {
        this.pages.addAll(pages)
    }
}