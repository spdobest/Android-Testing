package com.spm.androidtesting.account.viewmodel

import android.os.Handler
import android.util.Log
import android.view.View
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment
import com.spm.androidtesting.R
import com.spm.androidtesting.account.repository.LoginRepository
import com.spm.androidtesting.utils.CommonUtils
import org.koin.dsl.module

val loginviewModel = module {
    factory { LoginViewModel(get()) }
}

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel(), LifecycleObserver,
    Observable {


    /**
     * LifeCycle addresses the Android lifecycle problems in effective and easy ways. It has two main parts.
     * LifecycleOwner exposes its state changes, and LifecycleObserver listens to these changes to make appropriate steps.
     *
    LiveData, on the other hand, leverages reactive programming which helps us manipulate data easily.
    It shares some similarities with Stream in Java 8 and Observer(or Flowable) in RxJava. However,
    LiveData has an advantage is that it is lifecycle-aware specific for Android. So, it works closely with LifeCycle components.
     */

    lateinit var fragment: Fragment

    var progressVisibility: ObservableBoolean = ObservableBoolean()

    @Bindable
    var emailStr = ObservableField<String>()

    @Bindable
    var passwordStr = ObservableField<String>()


    var emailError = ObservableField<String>("")

    var passwordError = ObservableField<String>("")

    var email: String = ""
    var password: String = ""

    fun setEmail_Id(email: String) {
        this.email = email
        emailStr.set(email)
    }

    fun getEmail_Id(): String {
        return email
    }

    fun setPwd(pwd: String) {
        this.password = pwd
        passwordStr.set(pwd)
    }

    fun getPwd(): String {
        return password
    }

    fun onLoginClicked(view: View) {
        progressVisibility.set(true)

        if (!CommonUtils.isValidEmail(email)) {
            emailError.set("Invalid Email Id")
        }

        if (password.length < 6) {
            passwordError.set("Password can not be less than 6")
        }

        NavHostFragment.findNavController(fragment).navigate(R.id.homeTestFragment, null)

        Handler().postDelayed({
            progressVisibility.set(false)
        }, 3000)
    }

    fun onRegisterClicked(view: View) {
        NavHostFragment.findNavController(fragment).navigate(R.id.registerTestFragment, null)
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

    fun setFragmentContext(fragment: Fragment) {
        this.fragment = fragment
    }

}