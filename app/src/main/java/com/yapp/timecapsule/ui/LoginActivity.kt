package com.yapp.timecapsule.ui

import android.app.Application
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.yapp.timecapsule.R
import com.yapp.timecapsule.base.BaseActivity
import com.yapp.timecapsule.base.BaseViewModel
import com.yapp.timecapsule.custom.SingleLiveEvent
import com.yapp.timecapsule.databinding.ActivityLoginBinding
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val mViewModel: LoginViewModel by viewModel()

    override fun dataBindingInit() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        binding.viewModel = mViewModel
    }

    override fun onCreate() {

    }

    override fun observeViewModel() {
        mViewModel.kakaoLoginButtonClicked observe {
            binding.comKakaoLogin.performClick()
        }
    }
}

class LoginViewModel(application: Application): BaseViewModel(application) {
    val kakaoLoginButtonClicked = SingleLiveEvent<Unit>()
}