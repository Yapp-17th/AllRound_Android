package com.yapp.timecapsule

import android.app.Application
import com.kakao.auth.KakaoSDK
import com.yapp.timecapsule.init.AppStatus
import com.yapp.timecapsule.init.KakaoSDKAdapter
import com.yapp.timecapsule.init.TimeLifeCycleCallback
import com.yapp.timecapsule.utils.SharedPreferenceHelper

class TimeApplication: Application() {

    companion object {
        var mAppStatus: AppStatus = AppStatus.FOREGROUND
    }

    override fun onCreate() {
        super.onCreate()

        KakaoSDK.init(KakaoSDKAdapter(this@TimeApplication))

        registerActivityLifecycleCallbacks(TimeLifeCycleCallback)

        SharedPreferenceHelper.init(this)
    }

    fun getAppStatus(): AppStatus = mAppStatus

    fun isForeground(): Boolean = mAppStatus.priority > AppStatus.BACKGROUND.priority
}