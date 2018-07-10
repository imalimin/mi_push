package com.lmy.mipush

import android.content.Context
import android.util.Log
import com.lmy.mipush.impl.MiPushImpl
import com.xiaomi.mipush.sdk.MiPushMessage

/**
 * Created by lmyooyo@gmail.com on 2018/7/10.
 */
class DefaultNewMessageListener(context: Context) : MiPushImpl.OnNewMessageListener {
    companion object {
        private val TAG = "DefaultNewMessage"
    }

    override fun onNewMessage(message: MiPushMessage) {
        Log.e(TAG, "onNewMessage: $message")
    }

    override fun onNewMessageClick(message: MiPushMessage) {
        Log.e(TAG, "onNewMessageClick: $message")
    }
}