package com.lmy.mipush.impl

import android.app.ActivityManager
import android.content.ContentValues.TAG
import android.content.Context
import android.text.TextUtils
import android.util.Log
import com.lmy.mipush.DefaultNewMessageListener
import com.lmy.mipush.Push
import com.xiaomi.channel.commonutils.logger.LoggerInterface
import com.xiaomi.mipush.sdk.Logger
import com.xiaomi.mipush.sdk.MiPushClient
import com.xiaomi.mipush.sdk.MiPushMessage


/**
 * Created by lmyooyo@gmail.com on 2018/7/10.
 */
class MiPushImpl : Push {
    companion object {
        val instance = MiPushImpl()
    }

    private lateinit var context: Context
    private val onNewMessageListeners = LinkedHashSet<OnNewMessageListener>()
    override fun attach(context: Context) {
        this.context = context
    }

    private fun shouldInit(): Boolean {
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val processInfos = am.runningAppProcesses
        val mainProcessName = context.packageName
        val myPid = android.os.Process.myPid()
        for (info in processInfos) {
            if (info.pid == myPid && mainProcessName == info.processName) {
                return true
            }
        }
        return false
    }

    override fun init(id: String, key: String) {
        if (shouldInit()) {
            Log.v(TAG, "init")
            MiPushClient.registerPush(context, id, key)
            addOnNewMessageListener(DefaultNewMessageListener(context))
        }
        //打开Log
        val newLogger = object : LoggerInterface {
            override fun setTag(tag: String) {
                // ignore
            }

            override fun log(content: String, t: Throwable) {
                Log.d(TAG, content, t)
            }

            override fun log(content: String) {
                Log.d(TAG, content)
            }
        }
        Logger.setLogger(context, newLogger)
    }

    override fun setUserAccount(account: String?) {
        if (TextUtils.isEmpty(account)) {
            val data = MiPushClient.getAllUserAccount(context)
            for (acc in data) {
                MiPushClient.unsetUserAccount(context, acc, null)
            }
            return
        }
        Log.e(TAG, "setUserAccount: $account")
        MiPushClient.setUserAccount(context, account, null)
    }

    override fun unregister(listener: Push.OnUnregistertListener) {
        Log.v(TAG, "unregister")
        MiPushClient.unregisterPush(context)
    }

    override val isConnected: Boolean
        get() = false

    fun addOnNewMessageListener(listener: OnNewMessageListener) {
        onNewMessageListeners.add(listener)
    }

    fun removeOnNewMessageListener(listener: OnNewMessageListener) {
        onNewMessageListeners.remove(listener)
    }

    fun newMessage(message: MiPushMessage) {
        for (l in onNewMessageListeners) {
            l.onNewMessage(message)
        }
    }

    fun newMessageClick(message: MiPushMessage) {
        for (l in onNewMessageListeners) {
            l.onNewMessageClick(message)
        }
    }

    interface OnNewMessageListener {
        fun onNewMessage(message: MiPushMessage)

        fun onNewMessageClick(message: MiPushMessage)
    }

    class OnNewMessageAdapter : OnNewMessageListener {

        override fun onNewMessage(message: MiPushMessage) {

        }

        override fun onNewMessageClick(message: MiPushMessage) {

        }
    }
}