package com.yapp.timecapsule.init

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.yapp.timecapsule.TimeApplication

object TimeLifeCycleCallback: Application.ActivityLifecycleCallbacks {
    private var running = 0

    override fun onActivityPaused(activity: Activity) {}

    override fun onActivityStarted(activity: Activity) {
        if (++ running == 1) TimeApplication.mAppStatus = AppStatus.RETURNED_TO_FOREGROUND
        else if (running > 1) TimeApplication.mAppStatus = AppStatus.FOREGROUND
    }

    override fun onActivityDestroyed(activity: Activity) {}

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

    override fun onActivityStopped(activity: Activity) {
        if (--running == 0) TimeApplication.mAppStatus = AppStatus.BACKGROUND
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}

    override fun onActivityResumed(activity: Activity) {}
}