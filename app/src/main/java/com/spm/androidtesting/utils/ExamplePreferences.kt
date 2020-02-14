package com.spm.androidtesting.utils

import android.content.Context
import android.content.SharedPreferences

class ExamplePreferences(context: Context) {
    private val preferences: SharedPreferences =
        context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
    private val showFragmentKey = "showFragment"

    fun storeShouldShowFragment(shouldShow: Boolean) {
        preferences.edit().putBoolean(showFragmentKey, shouldShow).apply()
    }

    fun getShouldShowFragment() =   preferences.getBoolean(showFragmentKey, false)
}