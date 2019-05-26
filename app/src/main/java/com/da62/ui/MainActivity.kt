package com.da62.ui

import android.os.Bundle
import androidx.navigation.findNavController
import com.da62.R
import com.da62.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSupportNavigateUp() = findNavController(R.id.main_fragment).navigateUp()
}
