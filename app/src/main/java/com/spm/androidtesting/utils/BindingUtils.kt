package com.spm.androidtesting.utils

import android.graphics.drawable.Drawable
import android.text.TextUtils
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.spm.androidtesting.R
import com.spm.androidtesting.model.books.Book


object BindingUtils {


    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageUrl(view: AppCompatImageView, isEnable: Boolean) {
        var resourceId =
            if (isEnable) view.context.getDrawable(R.drawable.ic_heart_enable) else view.context.getDrawable(
                R.drawable.ic_heart_disable
            )
        view.setImageDrawable(resourceId)
    }

    @JvmStatic
    @BindingAdapter("android:error")
    fun onError(edittext: AppCompatEditText, error: String) {
        if (TextUtils.isEmpty(error)) {
            edittext.error = null
        } else {
            edittext.error = error
        }
    }

    @BindingAdapter(value = ["imageUrl", "placeholder"], requireAll = false)
    @JvmStatic
    fun loadImageInCircle(
        view: AppCompatImageView,
        imageUrl: String?,
        placeholder: Drawable? = null
    ) {
        Glide.with(view.context)
            .load(imageUrl)
            .apply(RequestOptions.circleCropTransform())
            .error(placeholder)
            .into(view)
    }

    @BindingAdapter("adapter")
    fun adapter(recyclerView: RecyclerView, listData: ArrayList<Book>) {
        //  recyclerView.adapter = BooksAdapter(listData, this)
    }

}