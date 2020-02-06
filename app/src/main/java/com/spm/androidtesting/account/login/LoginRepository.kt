package com.spm.androidtesting.account.login

import com.spm.androidtesting.network.BookApiService

class LoginRepository(private val bookAPi: BookApiService) {
    suspend fun getWeather() = bookAPi.getListOfBooks("")
}