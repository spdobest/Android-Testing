package com.spm.androidtesting.account.repository

import com.spm.androidtesting.network.BookApiService
import org.koin.dsl.module

val registerRepository = module {
    factory { RegisterRepository(get()) }
}
class RegisterRepository(private val bookAPi: BookApiService) {
    suspend fun getWeather() = bookAPi.getListOfBooks("")
}