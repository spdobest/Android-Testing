package com.spm.androidtesting.account.register

import com.spm.androidtesting.network.BookApiService

class RegisterRepository(private val bookAPi: BookApiService) {
    suspend fun getWeather() = bookAPi.getListOfBooks("")
}