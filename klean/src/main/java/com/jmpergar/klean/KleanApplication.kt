package com.jmpergar.klean

import android.app.Application
import android.os.StrictMode
import com.github.pedrovgs.lynx.LynxShakeDetector

abstract class KleanApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setUpStrictMode()
        setUpLynx()
    }

    private fun setUpStrictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build())
            StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build())
        }
    }

    private fun setUpLynx() {
        // Is need to add this to main manifest
        // <activity android:name="com.github.pedrovgs.lynx.LynxActivity"/>
        if (isDebuggable()) {
            LynxShakeDetector(this).init()
        }
    }

    abstract fun isDebuggable(): Boolean
}