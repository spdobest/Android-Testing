package com.spm.androidtesting.di

import com.spm.androidtesting.account.HomeTestFragment
import com.spm.androidtesting.account.LoginTestFragment
import com.spm.androidtesting.account.RegisterTestFragment
import org.koin.dsl.module

val fragmentModule = module {
//    factory { LoginTestFragment() }
    factory { RegisterTestFragment() }
    factory { HomeTestFragment() }
}