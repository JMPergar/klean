package com.jmpergar.klean

import android.app.Application
import android.os.StrictMode

abstract class KleanApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setUpStrictMode()
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
}