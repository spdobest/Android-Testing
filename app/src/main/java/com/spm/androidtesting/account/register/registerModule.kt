package com.spm.androidtesting.account.register

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val registerModule = module {
    factory { RegisterRepository(get()) }
    viewModel { RegisterViewModel(get()) }
}