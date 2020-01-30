package com.spm.androidtesting.di

import com.spm.androidtesting.account.repository.HomeRepository
import com.spm.androidtesting.account.repository.LoginRepository
import com.spm.androidtesting.account.repository.RegisterRepository
import org.koin.dsl.module

val repositoryModule = module {
//    factory { LoginRepository(get()) }
    factory { HomeRepository(get()) }
    factory { RegisterRepository(get()) }
}