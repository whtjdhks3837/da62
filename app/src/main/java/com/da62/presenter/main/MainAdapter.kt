package com.da62.presenter.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.da62.databinding.ItemMainGridBinding
import com.da62.databinding.ItemMainListBinding
import com.da62.model.ListType
import com.da62.model.Plant

class MainAdapter(
    private val eventListener: MainEventListener,
    private val lifecycleOwner: LifecycleOwner,
    viewType: ListType = ListType.LIST
) : ListAdapter<Plant, RecyclerView.ViewHolder>(mainDiffUtil) {

    private var isViewType = viewType

    private companion object {
        private const val LIST = 0
        private const val GRID = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            LIST -> {
                val binding = ItemMainListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                MainViewListHolder(binding, eventListener, lifecycleOwner)
            }
            else -> {
                val binding = ItemMainGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                MainViewGridHolder(binding, eventListener, lifecycleOwner)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (isViewType) {
            ListType.LIST -> (holder as MainViewListHolder).bind(getItem(position), position)
            else -> (holder as MainViewGridHolder).bind(getItem(position), position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (isViewType) {
            ListType.LIST -> LIST
            else -> GRID
        }
    }

    fun setViewType(type: ListType) {
        isViewType = type
    }

}

class MainViewListHolder(
    private val binding: ItemMainListBinding,
    private val eventListener: MainEventListener,
    private val lifecycleOwner: LifecycleOwner
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(plant: Plant, position: Int) {
        binding.eventListener = eventListener
        binding.plant = plant
        binding.position = position
        binding.lifecycleOwner = lifecycleOwner
        binding.executePendingBindings()
    }
}

class MainViewGridHolder(
    private val binding: ItemMainGridBinding,
    private val eventListener: MainEventListener,
    private val lifecycleOwner: LifecycleOwner
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(plant: Plant, position: Int) {
        binding.eventListener = eventListener
        binding.plant = plant
        binding.position = position
        binding.lifecycleOwner = lifecycleOwner
        binding.executePendingBindings()
    }
}

val mainDiffUtil = object : DiffUtil.ItemCallback<Plant>() {
    override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean = oldItem == newItem
}