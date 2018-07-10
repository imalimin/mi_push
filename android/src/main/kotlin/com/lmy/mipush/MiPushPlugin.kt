package com.lmy.mipush

import android.content.Context
import com.lmy.mipush.impl.MiPushImpl

import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.PluginRegistry
import io.flutter.plugin.common.PluginRegistry.Registrar

class MiPushPlugin() : MethodCallHandler, Push {
    override fun attach(context: Context) {

    }

    override val isConnected: Boolean
        get() = MiPushImpl.instance.isConnected

    override fun init(id: String, key: String) {
        MiPushImpl.instance.init(id, key)
    }

    override fun setUserAccount(account: String?) {
        MiPushImpl.instance.setUserAccount(account)
    }

    override fun unregister(listener: Push.OnUnregistertListener) {
        MiPushImpl.instance.unregister(listener)
    }

    companion object {
        @JvmStatic
        fun registerWith(registrar: Registrar): Unit {
            val channel = MethodChannel(registrar.messenger(), "push.lmy.com/mi_push")
            channel.setMethodCallHandler(MiPushPlugin())
        }
    }

    override fun onMethodCall(call: MethodCall, result: Result): Unit {
        if (call.method.equals("init")) {
            init(call.argument("id"), call.argument("key"))
            result.success(true)
        } else if (call.method.equals("setUserAccount")) {
            setUserAccount(call.argument("account"))
            result.success(true)
        } else {
            result.notImplemented()
        }
    }
}
