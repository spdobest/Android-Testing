package com.spm.androidtesting.model.books

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Isbn(
    val isbn10: String,
    val isbn13: String
):Parcelable