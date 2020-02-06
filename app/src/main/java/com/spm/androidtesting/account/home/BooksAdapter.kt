package com.spm.androidtesting.account.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.spm.androidtesting.controller.DeleteListener
import com.spm.androidtesting.databinding.ItemBookBinding
import com.spm.androidtesting.model.books.Book
import com.spm.androidtesting.utils.BookDiffCallback


class BooksAdapter : RecyclerView.Adapter<BookViewholder>() {

    lateinit var listener: DeleteListener
    private val listBooks: MutableList<Book> = mutableListOf()

    fun setDeleteListener(listener: DeleteListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewholder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemBookBinding = ItemBookBinding.inflate(layoutInflater, parent, false)
        if (!::listener.isInitialized) {
            throw RuntimeException("DeleteListener needs to be set on BooksAdapter")
        }
        return BookViewholder(itemBinding, listener)
    }

    override fun getItemCount(): Int {
        return listBooks.size
    }

    override fun onBindViewHolder(holder: BookViewholder, position: Int) {
        listBooks[position].let { holder.setData(it) }
    }

    fun setData(newData: List<Book>) {
        val postDiffCallback = BookDiffCallback(listBooks, newData)
        val diffResult = DiffUtil.calculateDiff(postDiffCallback)
        listBooks.clear()
        listBooks.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }

    fun delete(book: Book) {
        val position = listBooks.indexOf(book)
        if (position != -1) {
            listBooks.remove(book)
            notifyItemRemoved(position)
        }
    }
}


class BookViewholder(
    private val bindingview: ItemBookBinding,
    private val listener: DeleteListener
) :
    RecyclerView.ViewHolder(bindingview.root) {

    fun setData(book: Book) {
        bindingview.book = book
        bindingview.deleteCallback = listener
    }

}