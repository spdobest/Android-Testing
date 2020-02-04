package com.spm.androidtesting.account.viewmodel

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.spm.androidtesting.account.repository.HomeRepository
import com.spm.androidtesting.controller.DeleteListener
import com.spm.androidtesting.model.books.Book

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel(), LifecycleObserver,
    DeleteListener {


    private val deleteBook = MutableLiveData<Book>()
    var progressVisibility: ObservableBoolean = ObservableBoolean()

    init {
        progressVisibility.set(true)
    }

    fun delete(): LiveData<Book> = deleteBook

    fun getAllBooks() = homeRepository.getBooksList()

    fun getBooks() {

        homeRepository.getBooksList().observeForever {
            Log.i("TAG", "value ")
            //  bookadapter.setData(it!!)
            progressVisibility.set(false)
        }
    }

    override fun delete(book: Book) {
        Log.e("HomeViewModel", "Delete ==> $book")
        deleteBook.value = book
    }
}