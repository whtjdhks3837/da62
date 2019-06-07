package com.da62.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.da62.model.Plant
import com.da62.presenter.main.MainAdapter

@BindingAdapter("mainItems")
fun setAddItems(recyclerView: RecyclerView, items: List<Plant>?) {
    items?.let { (recyclerView.adapter as MainAdapter).submitList(it) }
}