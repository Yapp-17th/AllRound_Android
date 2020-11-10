package com.yapp.timecapsule.base

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

abstract class BaseFragment : Fragment() {
    protected var mContext: Context? = null

    open fun checkLandscapeMode() {}

    open fun onConfigurationChanged() {
        // 가로모드 대응시
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        checkLandscapeMode()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mContext = activity
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onDestroy() {
        super.onDestroy()
        mContext = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        checkLandscapeMode()
        onConfigurationChanged()
    }

    /////////// custom method //////////////

    fun alert(
        title: String? = null,
        message: String? = null,
        pText: String? = null,
        nText: String? = null,
        pListener: (() -> Unit)? = null,
        nListener: (() -> Unit)? = null
    ) {
        mContext?.let {
            (mContext as BaseActivity).alert(title, message, pText, nText, pListener, nListener)
        }
    }

    fun shortToast(message: String?) {
        mContext?.let {
            (mContext as BaseActivity).shortToast(message)
        }
    }

    fun longToast(message: String?) {
        mContext?.let {
            (mContext as BaseActivity).longToast(message)
        }
    }

    infix fun <T> LiveData<T>.observe(block: (T) -> Unit) {
        this.observe(viewLifecycleOwner, Observer {
            block(it)
        })
    }
}