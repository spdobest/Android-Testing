package com.spm.androidtesting.account.login

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    factory { LoginRepository(get()) }
    viewModel { LoginViewModel(get()) }
}