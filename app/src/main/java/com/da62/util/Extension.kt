package com.da62.util

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

infix fun CompositeDisposable.add(d: Disposable) = this.add(d)