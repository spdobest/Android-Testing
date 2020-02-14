package com.spm.androidtesting.ui.fragment


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.spm.androidtesting.R
import com.spm.androidtesting.account.home.HomeRepository
import com.spm.androidtesting.account.home.HomeViewModel
import com.spm.androidtesting.databinding.FragmentTabBinding
import com.spm.androidtesting.model.books.BooksResponse
import com.spm.androidtesting.network.BookApiService
import com.spm.androidtesting.ui.NextActivity
import com.spm.androidtesting.ui.repository.FirstTabRepository
import com.spm.androidtesting.ui.viewmodel.TabViewModel
import com.spm.androidtesting.utils.ExamplePreferences
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_tab.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.GlobalContext.get
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module

val firstTabModule = module(override=true){
    single { FirstTabRepository(get()) }
    viewModel { TabViewModel(get()) }
}

class FirstTabFragment : Fragment() {

    private val disposableList = CompositeDisposable()

    private val tabViewmodel: TabViewModel by viewModel()

    val examplePreferences: ExamplePreferences by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        loadKoinModules(firstTabModule)
        super.onCreate(savedInstanceState)
        Log.i(TAG, "OnCreate()")
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.i(TAG, "onCreateView")
        var binding: FragmentTabBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_tab,
                container,
                false
            )

        binding.viewmodel = tabViewmodel
        lifecycle.addObserver(tabViewmodel)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvMessage.text = "FIRST TAB"
        tvMessage.setOnClickListener {
            examplePreferences.storeShouldShowFragment(true)
            activity?.startActivity(Intent(activity, NextActivity::class.java))
        }
        Log.i(TAG, "onViewCreated()")

        progressBarLoading?.visibility = View.VISIBLE

        tabViewmodel.getBooksList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<BooksResponse> {
                override fun onComplete() {
                    Log.d("onComplete", "onComplete")
                    progressBarLoading?.visibility = View.GONE
                }

                override fun onSubscribe(d: Disposable) {
                    disposableList.add(d)
                    progressBarLoading?.visibility = View.GONE
                }

                override fun onNext(t: BooksResponse) {
                    Log.d("onNext", t.toString())
                    Log.i("TAG","RESPONSE "+t.status)
                    progressBarLoading?.visibility = View.GONE
                }

                override fun onError(e: Throwable) {
                    Log.d("onError", e.localizedMessage)
                    progressBarLoading?.visibility = View.GONE
                }
            })

    }

    companion object {
        val TAG = FirstTabFragment::class.java.simpleName
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstTabFragment().apply {
                arguments = Bundle().apply {}
            }
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume()")
    }

    override fun onPause() {
        super.onPause()
        disposableList.clear()
        Log.i(TAG, "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop()")
        disposableList.dispose()
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart()")
    }

    override fun onDestroyView() {
        unloadKoinModules(firstTabModule)
        super.onDestroyView()
        Log.i(TAG, "onDestroyView()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy()")
    }
}
