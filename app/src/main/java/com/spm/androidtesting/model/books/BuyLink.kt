package com.spm.androidtesting.model.books

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BuyLink(
    val name: String,
    val url: String
):Parcelable