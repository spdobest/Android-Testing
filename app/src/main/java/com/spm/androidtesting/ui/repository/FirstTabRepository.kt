package com.spm.androidtesting.ui.repository

import com.spm.androidtesting.model.books.BooksResponse
import com.spm.androidtesting.network.BookApiService
import io.reactivex.Observable

class FirstTabRepository(private val bookApiService: BookApiService) {

    fun getBookList(): Observable<BooksResponse> =  bookApiService.getListOfBooksUsingRx("y5nWAAK3Ou4OgGu5VZy4rhrQi4TTeluR")

}