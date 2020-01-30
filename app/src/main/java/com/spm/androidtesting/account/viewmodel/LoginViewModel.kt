package com.spm.androidtesting.account.viewmodel

import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.databinding.*
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.spm.androidtesting.utils.CommonUtils


class LoginViewModel : ViewModel(), LifecycleObserver,Observable {


    /**
     * LifeCycle addresses the Android lifecycle problems in effective and easy ways. It has two main parts.
     * LifecycleOwner exposes its state changes, and LifecycleObserver listens to these changes to make appropriate steps.
     *
    LiveData, on the other hand, leverages reactive programming which helps us manipulate data easily.
    It shares some similarities with Stream in Java 8 and Observer(or Flowable) in RxJava. However,
    LiveData has an advantage is that it is lifecycle-aware specific for Android. So, it works closely with LifeCycle components.
     */


    var progressVisibility: ObservableBoolean = ObservableBoolean()

    var email = ""

    var password = ""

    @InverseBindingAdapter(attribute = "app:errorEmail")
    fun getError(view: AppCompatEditText) : String {
        return ""
    }

    @Bindable
    fun getEmailString() = email

    fun setEmailString(email : String){
        this.email = email
    }

    @Bindable
    fun getPasswordString() = password

    fun setPasswordString(email : String){
        this.password = email
    }



    var errorEmail = ObservableField<String>()
    var errorPassword = ObservableField<String>()


    fun onLoginClicked(view: View) {
        progressVisibility.set(true)

        Handler().postDelayed(Runnable {
            progressVisibility.set(false)
        }, 3000)
    }


    fun onTextChangedEmail(
        s: CharSequence,
        start: Int,
        before: Int,
        count: Int
    ) {
        Log.w("tag", "onTextChanged $s")

    }

    fun onTextChangedPassword(
        s: CharSequence,
        start: Int,
        before: Int,
        count: Int
    ) {
        Log.w("tag", "onTextChanged $s")
        if (password!!.length < 6){
            errorPassword.set("Password must be ")
        }

    }

    @BindingAdapter("app:error")
    fun onError(edittext: AppCompatEditText, error: String) {
        edittext.error = error
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun getData() {
//        progressVisibility = true
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun start() {
        Log.e("TAG", "================================>>>> START lifecycle owner STARTED")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stop() {
        Log.e("TAG", "================================>>>> STOP lifecycle owner STOPED")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun resume() {
        Log.e("TAG", "================================>>>> RESUME lifecycle owner STARTED")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun pause() {
        Log.e("TAG", "================================>>>> PAUSE lifecycle owner STARTED")
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}