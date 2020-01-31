package com.spm.androidtesting.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.spm.androidtesting.account.viewmodel.HomeViewModel
import com.spm.androidtesting.databinding.ItemBookBinding
import com.spm.androidtesting.model.books.Book

class BookViewholder(val bindingview: ItemBookBinding) : RecyclerView.ViewHolder(bindingview.root) {

    fun setData(book: Book,viewmodel: HomeViewModel,position:Int) {
        bindingview.book = book
        bindingview.viewmodel = viewmodel
        bindingview.position = position
    }

}