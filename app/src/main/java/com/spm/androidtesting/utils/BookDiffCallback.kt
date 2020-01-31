package com.spm.androidtesting.utils

import androidx.recyclerview.widget.DiffUtil
import com.spm.androidtesting.model.books.Book

class BookDiffCallback(
    private val oldPosts: List<Book>,
    private val newPosts: List<Book>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldPosts.size
    }

    override fun getNewListSize(): Int {
        return newPosts.size
    }

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return oldPosts[oldItemPosition].book_uri === newPosts[newItemPosition].book_uri
    }

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return oldPosts[oldItemPosition] == newPosts[newItemPosition]
    }

}