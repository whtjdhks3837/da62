package com.da62.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.da62.model.ListType
import com.da62.model.Plant
import com.da62.presenter.main.MainAdapter

@BindingAdapter("mainItems")
fun setAddItems(recyclerView: RecyclerView, items: List<Plant>?) {
    items?.let { (recyclerView.adapter as MainAdapter).submitList(it) }
}

@BindingAdapter("mainListType")
fun setListType(recyclerView: RecyclerView, type: ListType?) {
    type?.let {
        val manager = when (it) {
            ListType.LIST -> LinearLayoutManager(recyclerView.context, LinearLayoutManager.HORIZONTAL, false)
            else -> GridLayoutManager(recyclerView.context, 2)
        }

        recyclerView.run {
            layoutManager = manager
            (adapter as MainAdapter).setViewType(it)
        }
    }
}