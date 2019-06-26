package com.da62.presenter.write.plant.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.da62.R
import com.da62.databinding.FragmentPlantRegistSpeciesBinding
import com.da62.presenter.base.BaseFragment
import com.da62.presenter.write.plant.PlantRegistViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlantSpeciesFragment : BaseFragment<FragmentPlantRegistSpeciesBinding>() {

    val mViewModel by sharedViewModel<PlantRegistViewModel>()

    override val mResourceId: Int = R.layout.fragment_plant_regist_species

    companion object {
        fun createInstance() =
            PlantSpeciesFragment().apply {

            }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        super.onCreateView(inflater, container, savedInstanceState).apply {
            mBinding.viewModel = mViewModel
        }
}