package com.spm.androidtesting.account.home

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    factory { BooksAdapter() }
    factory { HomeRepository(get()) }
    viewModel { HomeViewModel(get(), get()) }
}