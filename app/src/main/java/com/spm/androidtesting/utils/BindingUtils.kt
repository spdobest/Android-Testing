package com.spm.androidtesting.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.spm.androidtesting.adapter.BooksAdapter
import com.spm.androidtesting.model.books.Book


class BindingUtils {
    // important code for loading image here
//    @BindingAdapter("bind:imageUrl")


    @BindingAdapter("bind:imageUrl")
    fun loadImage1(view: AppCompatImageView, url: String) {
        Glide.with(view.context)
            .load(url).apply(RequestOptions().circleCrop())
            .into(view)
    }


    /* @BindingAdapter("app:imageUrl")
     fun loadImage(view: AppCompatImageView, imageUrl: String) {
         Glide.with(view.context)
             .load(imageUrl).apply(RequestOptions().circleCrop())
             .into(view)
     }*/

    @BindingAdapter("app:adapter")
    fun adapter(recyclerView: RecyclerView, listData: ArrayList<Book>) {
        recyclerView.adapter = BooksAdapter(listData)
    }
}