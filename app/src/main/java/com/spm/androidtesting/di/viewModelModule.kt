package com.spm.androidtesting.di

import com.spm.androidtesting.account.viewmodel.HomeViewModel
import com.spm.androidtesting.account.viewmodel.LoginViewModel
import com.spm.androidtesting.account.viewmodel.RegisterViewModel
import org.koin.dsl.module

val viewModelModule = module {
//    factory { LoginViewModel(get()) }
    factory { RegisterViewModel(get()) }
    factory { HomeViewModel(get()) }
}