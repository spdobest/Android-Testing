package com.spm.androidtesting.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.spm.androidtesting.account.viewmodel.HomeViewModel
import com.spm.androidtesting.adapter.viewholder.BookViewholder
import com.spm.androidtesting.databinding.ItemBookBinding
import com.spm.androidtesting.model.books.Book
import com.spm.androidtesting.utils.BookDiffCallback


class BooksAdapter(var listBooks: ArrayList<Book>,val viewmodel:HomeViewModel) : RecyclerView.Adapter<BookViewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewholder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemBookBinding = ItemBookBinding.inflate(layoutInflater, parent, false)
        return BookViewholder(itemBinding)
    }

    override fun getItemCount(): Int {
        return listBooks.size ?: 0
    }

    override fun onBindViewHolder(holder: BookViewholder, position: Int) {
        listBooks.get(position)?.let { holder.setData(it,viewmodel,position) }
    }

    fun setData(newData: ArrayList<Book>) {
        if (listBooks != null) {
            val postDiffCallback = BookDiffCallback(listBooks, newData)
            val diffResult = DiffUtil.calculateDiff(postDiffCallback)
            listBooks.clear()
            listBooks.addAll(newData)
            diffResult.dispatchUpdatesTo(this)
        }
    }
}