package com.spm.androidtesting.account

import com.spm.androidtesting.network.BookApiService
import org.koin.dsl.module
import retrofit2.Retrofit

val accountModule = module {
    single {
        val retrofit: Retrofit = get()
        retrofit.create(BookApiService::class.java)
    }
}