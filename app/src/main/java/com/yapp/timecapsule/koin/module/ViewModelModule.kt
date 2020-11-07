package com.yapp.timecapsule.koin.module

import com.yapp.timecapsule.ui.LoginViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(androidApplication()) }
}