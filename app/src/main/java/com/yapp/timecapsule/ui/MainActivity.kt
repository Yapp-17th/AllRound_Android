package com.yapp.timecapsule.ui

import android.app.Application
import androidx.databinding.DataBindingUtil
import com.yapp.timecapsule.R
import com.yapp.timecapsule.base.BaseActivity
import com.yapp.timecapsule.base.BaseViewModel
import com.yapp.timecapsule.databinding.ActivityMainBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mViewModel: MainViewModel by viewModel()

    override fun dataBindingInit() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = mViewModel
    }

    override fun onCreate() {

    }
}

class MainViewModel(application: Application): BaseViewModel(application) {

}