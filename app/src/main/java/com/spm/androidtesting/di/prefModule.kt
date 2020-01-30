package com.spm.androidtesting.di

import com.spm.androidtesting.utils.ExamplePreferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val prefModule = module {
    single { ExamplePreferences(androidContext()) }
}