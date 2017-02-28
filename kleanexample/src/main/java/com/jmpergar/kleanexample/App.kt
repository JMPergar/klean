package com.jmpergar.kleanexample

import android.app.Application

class App : Application() {

    companion object {
        lateinit var instance: App
            private set
    }
}