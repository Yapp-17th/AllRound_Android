package com.yapp.timecapsule.utils

import android.content.Context
import android.content.SharedPreferences
import com.yapp.timecapsule.consts.PrefConst

object SharedPreferenceHelper {
    private lateinit var mPrefs: SharedPreferences

    fun init(context: Context) {
        mPrefs = context.getSharedPreferences(PrefConst.PREF_TIME_APP, 0)
    }

    fun <T> writePrefs(key: String?, value: T?) {
        if (!SharedPreferenceHelper::mPrefs.isInitialized) {
            return
        }

        if (value == null) {
            return
        }

        when (value) {
            is Int -> writePrefs(key, value as Int)
            is Long -> writePrefs(key, value as Long)
            is String -> writePrefs(key, value as String)
            is Boolean -> writePrefs(key, value as Boolean)
        }
    }

    private fun writePrefs(key: String?, value: Int) = mPrefs.edit().putInt(key, value).apply()

    private fun writePrefs(key: String?, value: Long) = mPrefs.edit().putLong(key, value).apply()

    private fun writePrefs(key: String?, value: String) = mPrefs.edit().putString(key, value).apply()

    private fun writePrefs(key: String?, value: Boolean) = mPrefs.edit().putBoolean(key, value).apply()


    fun getPrefsBooleanValue(key: String?, defaultValue: Boolean? = null): Boolean =
        if (!SharedPreferenceHelper::mPrefs.isInitialized) false
        else mPrefs.getBoolean(key, defaultValue ?: false)

    fun getPrefsIntValue(key: String?, defaultValue: Int? = null): Int =
        if (!SharedPreferenceHelper::mPrefs.isInitialized) 0
        else mPrefs.getInt(key, defaultValue ?: 0)

    fun getPrefsLongValue(key: String?, defaultValue: Long? = null): Long =
        if (!SharedPreferenceHelper::mPrefs.isInitialized) 0
        else mPrefs.getLong(key, defaultValue ?: 0)

    fun getPrefsStringValue(key: String?, defaultValue: String? = null): String =
        if (!SharedPreferenceHelper::mPrefs.isInitialized) ""
        else mPrefs.getString(key, defaultValue ?: "") ?: ""


    fun removePrefsValue(key: String?) {
        if (!SharedPreferenceHelper::mPrefs.isInitialized) {
            return
        }

        if (mPrefs.contains(key)) {
            mPrefs.edit().remove(key).apply()
        }
    }
}