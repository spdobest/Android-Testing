package com.spm.androidtesting.account.viewmodel

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableBoolean
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.spm.androidtesting.account.repository.HomeRepository
import com.spm.androidtesting.adapter.BooksAdapter
import com.spm.androidtesting.model.books.Book
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel(), LifecycleObserver {
    
    //create a new Job
    private val parentJob = Job()
    //create a coroutine context with the job and the dispatcher
    private val coroutineContext: CoroutineContext get() = parentJob + Dispatchers.Default
    //create a coroutine scope with the coroutine context
    private val scope = CoroutineScope(coroutineContext)

    lateinit var fragment: Fragment

//    val homeRepository:HomeRepository by lazy {
//        HomeRepository()
//    }

    var progressVisibility: ObservableBoolean = ObservableBoolean()

    var bookList = ArrayList<Book>()

    val bookadapter: BooksAdapter by lazy {
        BooksAdapter(bookList)
    }

    init {
        progressVisibility.set(true)

    }

    fun setFragmentContext(fragment: Fragment) {
        this.fragment = fragment
    }


    fun getBooks() {

        homeRepository.getBooksList().observeForever {
            Log.i("TAG", "value ")
            Log.i("asdas", it?.toString())
            bookList.addAll(it)
            bookadapter.notifyDataSetChanged()
            progressVisibility.set(false)
        }
    }

    @BindingAdapter("adapter")
    fun adapter(recyclerView: RecyclerView, listData: ArrayList<Book>) {
        recyclerView.adapter = BooksAdapter(listData)
    }

    fun cancelRequests() = coroutineContext.cancel()
}