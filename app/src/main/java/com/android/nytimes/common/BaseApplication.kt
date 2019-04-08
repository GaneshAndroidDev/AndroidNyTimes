package com.android.nytimes.common

import android.app.Application
import com.android.nytimes.BuildConfig
import timber.log.Timber

class BaseApplication : Application()
{
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
