package com.spm.androidtesting.account.viewmodel

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.spm.androidtesting.account.repository.HomeRepository
import com.spm.androidtesting.adapter.BooksAdapter
import com.spm.androidtesting.controller.DeleteListener
import com.spm.androidtesting.model.books.Book

class HomeViewModel(private val homeRepository: HomeRepository, val bookAdapter: BooksAdapter) :
    ViewModel(), LifecycleObserver,
    DeleteListener {

    private val deleteBook = MutableLiveData<Book>()
    var progressVisibility: ObservableBoolean = ObservableBoolean()

    init {
        progressVisibility.set(true)
        bookAdapter.setDeleteListener(this)
        getAllBooks()
    }

    fun delete(): LiveData<Book> = deleteBook

//    fun getAllBooks() = homeRepository.getBooksList()

    fun getAllBooks() {
        homeRepository.getBooksList().observeForever {
            if (it.size > 0) {
                Log.i("TAG", "value ")
                progressVisibility.set(false)
                bookAdapter.setData(it)
            }
        }
    }

    override fun delete(book: Book) {
        Log.e("HomeViewModel", "Delete ==> $book")
        // deleteBook.value = book
        bookAdapter.delete(book)
    }
}