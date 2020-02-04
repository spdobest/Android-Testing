package com.spm.androidtesting.utils

import android.os.Bundle
import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.spm.androidtesting.model.books.Book

class BookDiffCallback(
    private val oldBooks: List<Book>,
    private val newBooks: List<Book>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldBooks.size
    }

    override fun getNewListSize(): Int {
        return newBooks.size
    }

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return oldBooks[oldItemPosition].book_uri === newBooks[newItemPosition].book_uri
    }

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return oldBooks[oldItemPosition] == newBooks[newItemPosition]
    }

    @Nullable
    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        //  return super.getChangePayload(oldPosition, newPosition)

        val oldBook: Book = oldBooks[oldPosition]
        val newBook: Book = newBooks[newPosition]
        val bundle = Bundle()
        if (oldBook.book_uri !== newBook.book_uri) {
            bundle.putInt("image", 123)
        }
        if (!oldBook.author.equals(newBook.author, false)) {
            bundle.putString("name", newBook.author)
        }
        return bundle
    }
}