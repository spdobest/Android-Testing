package com.spm.androidtesting.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.spm.androidtesting.databinding.ItemBookBinding
import com.spm.androidtesting.model.books.Book

class BookViewholder(val bindingview: ItemBookBinding) : RecyclerView.ViewHolder(bindingview.root) {

    fun setData(book: Book) {
        bindingview.book = book
    }

}