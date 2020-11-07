package com.yapp.timecapsule.base

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yapp.timecapsule.R

abstract class BaseActivity : AppCompatActivity() {
    abstract fun dataBindingInit()

    abstract fun onCreate()

    open fun checkLandscapeMode() {}

    open fun onConfigurationChanged() {
        // 가로모드 대응시
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        settingWindow()

        checkLandscapeMode()

        dataBindingInit()

        onCreate()
    }

    private fun settingWindow() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        checkLandscapeMode()
        onConfigurationChanged()
    }

    ////////////////   custom method   ///////////////////

    fun alert(
        title: String? = null,
        message: String? = null,
        pText: String? = null,
        nText: String? = null,
        pListener: (() -> Unit)? = null,
        nListener: (() -> Unit)? = null
    ) {
        val dialog = MaterialAlertDialogBuilder(this)

        var realMessage = message
        var positiveText = pText
        var negativeText = nText

        if (pText == null || pListener == null || nText == null || nListener == null) {
            positiveText = getString(R.string.confirm)
        }

        if (pListener != null && pText == null) {
            positiveText = getString(R.string.confirm)
        }

        if (nListener != null && nText == null) {
            negativeText = getString(R.string.cancel)
        }

        if (title == null && message == null) {
            realMessage = getString(R.string.empty_message)
        }

        title?.let { dialog.setTitle(it) }
        realMessage?.let { dialog.setMessage(it) }
        positiveText?.let { dialog.setPositiveButton(it) { _, _ -> pListener?.invoke() } }
        negativeText?.let { dialog.setNegativeButton(it) { _, _ -> nListener?.invoke() } }

        dialog.show()
    }

    fun shortToast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun longToast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}