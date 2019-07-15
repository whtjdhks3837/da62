package com.da62.presenter.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.da62.databinding.ItemDetailInfoBinding
import com.da62.model.Plant

class DetailInfoAdapter(
    private val lifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val plantList = mutableListOf<Plant>()

    private companion object {
        private const val A = 0
        private const val B = 1
        private const val C = 2
        private const val D = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val binding =
            ItemDetailInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailInfoAViewHolder(binding, lifecycleOwner)
    }

    fun addItems(plantList: List<Plant>) {
        this.plantList.addAll(plantList)
    }

    /* override fun getItemViewType(position: Int): Int {
         return when (position) {
             0 -> A
             1 -> B
             2 -> C
             3 -> D
             else -> 0
         }
     }*/


    override fun getItemCount(): Int = 4

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DetailInfoAViewHolder).bind(plantList[position])
        /*  when(position){
             0 -> (holder as DetailInfoAViewHolder).bind(plantList[position])
         }*/
    }
}

class DetailInfoAViewHolder(
    private val binding: ItemDetailInfoBinding,
    private val lifecycleOwner: LifecycleOwner
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(plant: Plant) {
        binding.plant = plant
        binding.lifecycleOwner = lifecycleOwner
        binding.executePendingBindings()
    }
}