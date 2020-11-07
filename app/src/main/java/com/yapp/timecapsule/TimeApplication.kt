package com.yapp.timecapsule

import android.app.Application
import com.kakao.auth.KakaoSDK
import com.yapp.timecapsule.init.AppStatus
import com.yapp.timecapsule.init.KakaoSDKAdapter
import com.yapp.timecapsule.init.TimeLifeCycleCallback
import com.yapp.timecapsule.koin.module.networkModule
import com.yapp.timecapsule.koin.module.viewModelModule
import com.yapp.timecapsule.utils.CLog
import com.yapp.timecapsule.utils.SharedPreferenceHelper
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import java.io.IOException
import java.net.SocketException

class TimeApplication: Application() {

    companion object {
        var mAppStatus: AppStatus = AppStatus.FOREGROUND
    }

    override fun onCreate() {
        super.onCreate()

        KakaoSDK.init(KakaoSDKAdapter(this@TimeApplication))

        registerActivityLifecycleCallbacks(TimeLifeCycleCallback)

        SharedPreferenceHelper.init(this)

        startKoin {
            androidLogger()
            androidContext(this@TimeApplication)
            modules(listOf(networkModule, viewModelModule))
        }

        registerRxErrorHandler()
    }

    fun getAppStatus(): AppStatus = mAppStatus

    fun isForeground(): Boolean = mAppStatus.priority > AppStatus.BACKGROUND.priority

    private fun registerRxErrorHandler() {
        RxJavaPlugins.setErrorHandler { e ->
            var error = e
            if (error is UndeliverableException) {
                error = e.cause
            }
            if (error is IOException || error is SocketException) {
                // fine, irrelevant network problem or API that throws on cancellation
                return@setErrorHandler
            }
            if (error is InterruptedException) {
                // fine, some blocking code was interrupted by a dispose call
                return@setErrorHandler
            }
            if (error is NullPointerException || error is IllegalArgumentException) {
                // that's likely a bug in the application
                Thread.currentThread().uncaughtExceptionHandler
                    .uncaughtException(Thread.currentThread(), error)
                return@setErrorHandler
            }
            if (error is IllegalStateException) {
                // that's a bug in RxJava or in a custom operator
                Thread.currentThread().uncaughtExceptionHandler
                    .uncaughtException(Thread.currentThread(), error)
                return@setErrorHandler
            }
            CLog.w("Undeliverable exception received, not sure what to do $error")
        }
    }
}