package com.spm.androidtesting.network

import com.spm.androidtesting.model.books.BooksResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface BookApiService {
    @GET("lists/current/hardcover-fiction.json?")
    fun getListOfBooks(@Query("api-key") api_key: String): Call<BooksResponse>?
}