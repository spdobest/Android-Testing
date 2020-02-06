package com.spm.androidtesting.account.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.spm.androidtesting.model.books.Book
import com.spm.androidtesting.model.books.BooksResponse
import com.spm.androidtesting.network.BookApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeRepository(private val bookAPi: BookApiService) {

    var observableBooks = MutableLiveData<List<Book>>()
    var observableError = MutableLiveData<String>()

    suspend fun getWeather() = bookAPi.getListOfBooks("")

    fun getBooksList(): LiveData<List<Book>> {
        try {
            val call = bookAPi.getListOfBooks("y5nWAAK3Ou4OgGu5VZy4rhrQi4TTeluR")
            call?.enqueue(
                object : Callback<BooksResponse?> {
                    override fun onResponse(
                        call: Call<BooksResponse?>?,
                        response: Response<BooksResponse?>
                    ) {
                        Log.i("TAG", "Size is " + response)
                        observableBooks.value = response.body()?.results?.books
                    }

                    override fun onFailure(
                        call: Call<BooksResponse?>?,
                        t: Throwable?
                    ) {
                        observableError.value = "Server Error"
                    }
                }
            )

        } catch (e: Exception) {
            e.message
            observableError.value = "Server Error"
        }
        return observableBooks
    }

    fun getErros(): LiveData<String> {
        return observableError
    }

}