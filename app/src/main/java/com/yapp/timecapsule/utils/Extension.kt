package com.yapp.timecapsule.utils

import android.os.Handler
import android.os.Looper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

infix fun Disposable.addTo(compositeDisposable: CompositeDisposable) = compositeDisposable.add(this)

fun mainThread(block: () -> Unit) = Handler(Looper.getMainLooper()).post(block)

fun delay(time: Long, block: () -> Unit) = Handler(Looper.getMainLooper()).postDelayed(block, time)