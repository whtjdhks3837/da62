package com.da62.presenter.detail

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.da62.model.Plant

@BindingAdapter("addPlantsInfo")
fun addPlantsInfo(recyclerView: RecyclerView, plantList: List<Plant>?) {
    plantList?.let {
        (recyclerView.adapter as DetailInfoAdapter).addItems(it)
    }
}