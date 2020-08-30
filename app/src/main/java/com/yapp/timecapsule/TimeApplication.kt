package com.yapp.timecapsule

import android.app.Application
import com.kakao.auth.KakaoSDK
import com.yapp.timecapsule.thirdparty.KakaoSDKAdapter

class TimeApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        KakaoSDK.init(KakaoSDKAdapter(this@TimeApplication))
    }
}