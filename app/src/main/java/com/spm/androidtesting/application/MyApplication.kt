package com.spm.androidtesting.application

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import com.spm.androidtesting.di.networkModule
import com.spm.androidtesting.di.prefModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application(), LifecycleObserver {

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)

        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                listOf(
                    prefModule,
                    networkModule
                )
            )
        }
    }
}