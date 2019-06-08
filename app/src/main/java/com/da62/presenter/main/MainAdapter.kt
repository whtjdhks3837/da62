package com.da62.presenter.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.da62.databinding.ItemMainBinding
import com.da62.model.Plant

class MainAdapter(
    private val eventListener: MainEventListener,
    private val lifecycleOwner: LifecycleOwner
) : ListAdapter<Plant, MainViewHolder>(mainDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding, eventListener, lifecycleOwner)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

}

class MainViewHolder(
    private val binding: ItemMainBinding,
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