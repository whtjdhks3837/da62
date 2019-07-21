package com.da62.util

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.da62.R
import com.da62.model.Card
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
        /*   val manager = when (it) {
               ListType.LIST -> GridLayoutManager(
                   recyclerView.context,
                   1,
                   LinearLayoutManager.HORIZONTAL,
                   false
               )

               else -> GridLayoutManager(recyclerView.context, 2)
           }
   */
        recyclerView.run {
            // layoutManager = manager
            val manager = layoutManager as GridLayoutManager
            if (manager.spanCount == 1) {
                manager.spanCount = 3
            } else {
                manager.spanCount = 1
            }
            (adapter as MainAdapter).setViewType(it)
            adapter?.notifyItemRangeChanged(0, adapter?.itemCount ?: 0)
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


@BindingAdapter("growThumbNail")
fun growThumbNail(imageView: ImageView, grow: String?) {
    grow?.let {
        val src = when (it) {
            "TREE" -> R.drawable.ic_illust_2
            "DRUPE" -> R.drawable.ic_illust_5
            "GRASS" -> R.drawable.ic_illust_1
            "LEAF" -> R.drawable.ic_illust_3
            else -> R.drawable.ic_illust_4
        }

        Glide.with(imageView)
            .load(src)
            .into(imageView)
    }
}