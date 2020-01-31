package com.spm.androidtesting.di

import com.spm.androidtesting.account.repository.HomeRepository
import org.koin.dsl.module

val repositoryModule = module {
//    factory { LoginRepository(get()) }
    factory { HomeRepository(get()) }
}