package com.spm.androidtesting.di

import com.spm.androidtesting.account.HomeTestFragment
import org.koin.dsl.module

val fragmentModule = module {
    //    factory { LoginTestFragment() }
    factory { HomeTestFragment() }
}