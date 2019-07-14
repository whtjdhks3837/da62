package com.da62.presenter.write.plant.fragment

import android.content.Context
import android.graphics.drawable.StateListDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.da62.R
import com.da62.databinding.FragmentPlantRegistSpeciesBinding
import com.da62.databinding.ListPlantSpeciesSearchItemBinding
import com.da62.presenter.base.BaseFragment
import com.da62.presenter.write.plant.PlantRegistViewModel
import com.da62.util.toEditable
import org.jetbrains.anko.sdk27.coroutines.onFocusChange
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.*

class PlantSpeciesFragment : BaseFragment<FragmentPlantRegistSpeciesBinding>() {

    val viewModel by sharedViewModel<PlantRegistViewModel>()

    override val resourceId: Int = R.layout.fragment_plant_regist_species

    private lateinit var searchResultAdapter: SearchResultAdapter

    private val speciesIllustAsset = listOf(
        Pair(R.drawable.ic_illust_1, R.drawable.ic_illust_1_trans),
        Pair(R.drawable.ic_illust_2, R.drawable.ic_illust_2_trans),
        Pair(R.drawable.ic_illust_3, R.drawable.ic_illust_3_trans),
        Pair(R.drawable.ic_illust_4, R.drawable.ic_illust_4_trans),
        Pair(R.drawable.ic_illust_5, R.drawable.ic_illust_5_trans)
    )

    companion object {
        fun createInstance() =
            PlantSpeciesFragment().apply {

            }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        super.onCreateView(inflater, container, savedInstanceState).apply {
            binding.viewModel = viewModel
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        speciesImageObserve()
        speciesObserve()
        searchResultObserve()
    }

    private fun init() {
        createSelectorAssets().run {
            binding.cbContainer.children.toList().forEach {
                it.background = poll()
            }
        }
        binding.searchResultList.apply {
            layoutManager = LinearLayoutManager(context)
            searchResultAdapter = SearchResultAdapter {
                binding.editText.text = it.toEditable()
                searchResultAdapter.clear()
            }.apply {
                adapter = this
            }
        }
        //TODO : 여기부터
        binding.editText.onFocusChange { v, hasFocus ->
            if (!hasFocus) {
                searchResultAdapter.clear()
            }
        }
    }

    private fun createSelectorAssets() =
        context?.let { context ->
            LinkedList<StateListDrawable>().run {
                speciesIllustAsset.forEach {
                    offer(createSelectorAsset(context, it.first, it.second))
                }
                this
            }
        } ?: throw IllegalStateException()

    private fun createSelectorAsset(context: Context, @DrawableRes checkedResId: Int, @DrawableRes unCheckedResId: Int) =
        StateListDrawable().apply {
            addState(IntArray(1) { android.R.attr.state_checked }, ContextCompat.getDrawable(context, checkedResId))
            addState(IntArray(0), ContextCompat.getDrawable(context, unCheckedResId))
        }

    private fun speciesImageObserve() = viewModel.speciesImage.observe(this, Observer { view ->
        view?.let {
            binding.cbContainer.children.toList()
                .filter { it is CheckBox }
                .filterNot { it.id == view.id }
                .forEach { (it as CheckBox).isChecked = false }
        }
    })

    private fun speciesObserve() = viewModel.species.observe(this, Observer {
        viewModel.inputSpeciesText(it)
    })

    private fun searchResultObserve() = viewModel.searchResult.observe(this, Observer {
        searchResultAdapter.setItem(it)
    })
}

class SearchResultAdapter(private val click: (String) -> Unit) :
    RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {

    private val searchList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ListPlantSpeciesSearchItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false), click
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(searchList[position])
    }

    override fun getItemCount() = searchList.size

    fun setItem(searchList: List<String>) {
        this.searchList.clear()
        this.searchList.addAll(searchList)
        notifyDataSetChanged()
    }

    fun clear() {
        searchList.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: ListPlantSpeciesSearchItemBinding,
        private val click: (String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(text: String) {
            binding.result.text = text
            itemView.setOnClickListener {
                click(text)
            }
        }
    }
}