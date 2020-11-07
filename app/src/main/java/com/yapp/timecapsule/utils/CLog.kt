package com.yapp.timecapsule.utils

import android.util.Log

// custom Log
class CLog {
    companion object {
        private const val TAG = "TIME_LOG"

        fun w(string: String) = Log.w(TAG, string)

        fun d(string: String) = Log.d(TAG, string)

        fun e(string: String) = Log.e(TAG, string)

        fun i(string: String) = Log.i(TAG, string)

        fun v(string: String) = Log.v(TAG, string)
    }
}