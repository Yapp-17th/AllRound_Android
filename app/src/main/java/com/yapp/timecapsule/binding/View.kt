package com.yapp.timecapsule.binding

import android.view.View
import androidx.databinding.BindingAdapter
import com.yapp.timecapsule.custom.SingleLiveEvent

@BindingAdapter("onSingleLiveData")
fun onSingleLiveData(view: View, singleLiveEvent: SingleLiveEvent<*>) {
    view.setOnClickListener { singleLiveEvent.call() }
}