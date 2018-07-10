package com.lmy.mipush

import android.content.Context

/**
 * Created by lmyooyo@gmail.com on 2018/7/10.
 */
interface Push {
    fun attach(context: Context)
    val isConnected: Boolean
    fun init(id: String, key: String)

    fun setUserAccount(account: String?)

    fun unregister(listener: OnUnregistertListener)

    interface OnUnregistertListener {
        fun unregister()
    }
}