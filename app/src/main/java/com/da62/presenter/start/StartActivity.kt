package com.da62.presenter.start

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.da62.R
import com.da62.databinding.ActivityStartBinding
import com.da62.databinding.ItemStartInfoBinding
import com.da62.presenter.base.BaseActivity
import com.da62.presenter.login.LoginActivity
import com.da62.util.toDp
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk27.coroutines.onLayoutChange

data class StartPage(
    val imageResourceId: Int,
    val infoResourceId: Int
)

class StartActivity : BaseActivity() {

    companion object {
        const val PERIOD_MILLIS = 5000
    }

    private lateinit var binding: ActivityStartBinding

    private val pageResources = listOf(
        StartPage(R.drawable.start_image3, R.string.start_info3),
        StartPage(R.drawable.start_image2, R.string.start_info2),
        StartPage(R.drawable.start_image1, R.string.start_info1)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_start)
        binding.indicator.apply {
            setPages(pageResources.size - 1)
            select(0)
        }
        binding.viewFlipper.apply {
            pageResources.forEach { addView(createPage(this, it)) }
            setFlipInterval(PERIOD_MILLIS)
            startFlipping()
            onLayoutChange { _, _, _, _, _, _, _, _, _ ->
                val position = binding.viewFlipper.indexOfChild(binding.viewFlipper.currentView)
                binding.indicator.select(position)
            }
        }
        binding.start.setOnClickListener {
            startActivity(intentFor<LoginActivity>()).apply {
                finish()
            }
        }
    }

    private fun createPage(root: ViewGroup, startPage: StartPage) =
        DataBindingUtil.inflate<ItemStartInfoBinding>(
            LayoutInflater.from(root.context), R.layout.item_start_info, root, false
        ).apply {
            image.setImageResource(startPage.imageResourceId)
            infoText.text = getString(startPage.infoResourceId)
        }.root
}

class PageIndicator @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        orientation = HORIZONTAL
    }

    @DrawableRes
    private val selectedResourceId = R.drawable.start_indicator_selected

    @DrawableRes
    private val nonSelectedResourceId = R.drawable.start_indicator_non_selected

    private var selectedPosition = -1

    fun setPages(numOfPage: Int) {
        selectedPosition = -1
        removeAllViews()
        for (i in 0..numOfPage) {
            addView(createImage())
        }
    }

    private fun createImage() = View(context).apply {
        layoutParams = LayoutParams(toDp(context, 12f), toDp(context, 12f)).apply {
            rightMargin = toDp(context, 12f)
        }
        background = ContextCompat.getDrawable(context, nonSelectedResourceId)
    }

    fun select(position: Int) {
        if (selectedPosition != -1) {
            setImage(selectedPosition, nonSelectedResourceId)
        }
        selectedPosition = position
        setImage(selectedPosition, selectedResourceId)
    }

    private fun setImage(position: Int, @DrawableRes resourceId: Int) {
        if (position in 0..childCount) {
            getChildAt(position).background = ContextCompat.getDrawable(context, resourceId)
        }
    }
}