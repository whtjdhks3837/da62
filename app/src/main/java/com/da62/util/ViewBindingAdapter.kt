package com.da62.util

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
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

@BindingAdapter("goneUnless")
fun goneUnless(view: View, visible: Boolean?) {
    visible?.let {
        if (it) {
            view.visibility = VISIBLE
        } else {
            view.visibility = GONE
        }
    } ?: run {
        view.visibility = GONE
    }
}