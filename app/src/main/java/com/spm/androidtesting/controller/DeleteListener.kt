package com.spm.androidtesting.controller

import com.spm.androidtesting.model.books.Book

interface DeleteListener {
    fun delete(book: Book)
}