package com.yapp.timecapsule.ui

import android.app.Application
import android.content.Intent
import androidx.databinding.DataBindingUtil
import com.yapp.timecapsule.R
import com.yapp.timecapsule.base.BaseActivity
import com.yapp.timecapsule.base.BaseViewModel
import com.yapp.timecapsule.databinding.ActivitySplashBinding
import com.yapp.timecapsule.utils.delay
import org.koin.android.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity() {
    private lateinit var binding: ActivitySplashBinding
    private val mViewModel: SplashViewModel by viewModel()

    override fun dataBindingInit() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        binding.lifecycleOwner = this
        binding.viewModel = mViewModel
    }

    override fun onCreate() {

    }

    override fun onResume() {
        super.onResume()
        delay(3000) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}

class SplashViewModel(application: Application): BaseViewModel(application) {

}