package com.lmy.mipush.receiver

import android.content.Context
import android.util.Log
import com.lmy.mipush.impl.MiPushImpl
import com.xiaomi.mipush.sdk.MiPushCommandMessage
import com.xiaomi.mipush.sdk.MiPushMessage
import com.xiaomi.mipush.sdk.PushMessageReceiver


/**
 * Created by lmyooyo@gmail.com on 2018/7/10.
 */
class MiPushMessageReceiver : PushMessageReceiver() {
    companion object {
        private val TAG = "MiPushMessageReceiver"
    }

    /**
     * 透传消息
     *
     * @param context
     * @param miPushMessage
     */
    override fun onReceivePassThroughMessage(context: Context?, miPushMessage: MiPushMessage?) {
        super.onReceivePassThroughMessage(context, miPushMessage)
    }

    /**
     * 通知栏消息（用户点击通知栏时触发）
     *
     * @param context
     * @param miPushMessage
     */
    override fun onNotificationMessageClicked(context: Context?, miPushMessage: MiPushMessage) {
        super.onNotificationMessageClicked(context, miPushMessage)
        MiPushImpl.instance.newMessageClick(miPushMessage)
    }

    /**
     * 通知栏消息（消息到达客户端时触发，并且可以接收应用在前台时不弹出通知的通知消息）
     *
     * @param context
     * @param miPushMessage
     */
    override fun onNotificationMessageArrived(context: Context?, miPushMessage: MiPushMessage) {
        super.onNotificationMessageArrived(context, miPushMessage)
        MiPushImpl.instance.newMessage(miPushMessage)
    }

    override fun onReceiveMessage(context: Context?, miPushMessage: MiPushMessage?) {
        super.onReceiveMessage(context, miPushMessage)
    }

    /**
     * 接受客户端向服务器发送注册命令消息后返回的响应
     *
     * @param context
     * @param miPushCommandMessage
     */
    override fun onReceiveRegisterResult(context: Context?, miPushCommandMessage: MiPushCommandMessage?) {
        super.onReceiveRegisterResult(context, miPushCommandMessage)
        Log.v(TAG, "onReceiveRegisterResult: " + miPushCommandMessage!!.toString())
    }

    /**
     * 用来接收客户端向服务器发送命令消息后返回的响应
     *
     * @param context
     * @param miPushCommandMessage
     */
    override fun onCommandResult(context: Context?, miPushCommandMessage: MiPushCommandMessage?) {
        super.onCommandResult(context, miPushCommandMessage)
        Log.v(TAG, "onCommandResult: " + miPushCommandMessage!!.toString())
    }
}