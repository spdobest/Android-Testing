package com.spm.androidtesting.ui.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.spm.androidtesting.ui.repository.FirstTabRepository

class TabViewModel(private val repository: FirstTabRepository) : ViewModel(),LifecycleObserver{

    fun getBooksList() = repository.getBookList()
}