package com.spm.androidtesting.di

import com.spm.androidtesting.account.viewmodel.HomeViewModel
import com.spm.androidtesting.adapter.BooksAdapter
import org.koin.dsl.module

val viewModelModule = module {
    //    factory { LoginViewModel(get()) }
    factory { HomeViewModel(get()) }
    factory { BooksAdapter() }
}