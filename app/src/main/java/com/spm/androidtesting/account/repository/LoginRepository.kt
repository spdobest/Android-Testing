package com.spm.androidtesting.account.repository

import com.spm.androidtesting.network.BookApiService
import org.koin.dsl.module

val forecastModule = module {
    factory { LoginRepository(get()) }
}


class LoginRepository(private val bookAPi: BookApiService) {
    suspend fun getWeather() = bookAPi.getListOfBooks("")
}