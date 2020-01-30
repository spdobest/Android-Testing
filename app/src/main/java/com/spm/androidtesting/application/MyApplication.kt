package com.spm.androidtesting.application

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import com.spm.androidtesting.account.loginFragmentModule
import com.spm.androidtesting.account.repository.forecastModule
import com.spm.androidtesting.account.viewmodel.loginviewModel
import com.spm.androidtesting.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application(), LifecycleObserver {

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this)

        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                listOf(
                    prefModule,
                    viewModelModule,
                    repositoryModule,
                    networkModule,
                    fragmentModule,
                    loginviewModel,
                    loginFragmentModule,
                    forecastModule
                )
            )
        }
    }
}