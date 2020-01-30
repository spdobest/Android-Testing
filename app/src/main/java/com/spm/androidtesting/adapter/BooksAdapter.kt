package com.spm.androidtesting.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spm.androidtesting.adapter.viewholder.BookViewholder
import com.spm.androidtesting.databinding.ItemBookBinding
import com.spm.androidtesting.model.books.Book


class BooksAdapter(var listBooks: List<Book>) : RecyclerView.Adapter<BookViewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewholder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemBookBinding = ItemBookBinding.inflate(layoutInflater, parent, false)
        return BookViewholder(itemBinding)
    }
    override fun getItemCount(): Int {
        return listBooks.size ?: 0
    }

    override fun onBindViewHolder(holder: BookViewholder, position: Int) {
        holder.setData(listBooks[position])
    }
}