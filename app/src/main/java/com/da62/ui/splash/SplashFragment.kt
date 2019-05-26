package com.da62.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.da62.R
import com.da62.databinding.FragmentSplashBinding
import com.da62.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class SplashFragment : BaseFragment() {

    private val viewModel by viewModel<SplashViewModel>()
    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false).apply {
            viewModel = this@SplashFragment.viewModel
            lifecycleOwner = this@SplashFragment
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.openToMain.observe(this, Observer {
           findNavController().navigate(R.id.action_to_main)
        })
    }

}