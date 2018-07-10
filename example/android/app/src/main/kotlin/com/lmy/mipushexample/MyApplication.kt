package com.lmy.mipushexample

import android.content.Context
import android.util.Log
import com.lmy.mipush.impl.MiPushImpl

class MyApplication : io.flutter.app.FlutterApplication() {

    companion object {
        private val TAG = "MyApplication"
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MiPushImpl.instance.attach(base)
    }
}