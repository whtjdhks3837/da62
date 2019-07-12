package com.da62.util

import android.content.Context
import android.text.Editable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

infix fun CompositeDisposable.add(d: Disposable) = this.add(d)

fun dp2px(context: Context, dpValue: Float): Int {
    val scale = context.resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}

fun String.toEditable() = Editable.Factory.getInstance().newEditable(this)