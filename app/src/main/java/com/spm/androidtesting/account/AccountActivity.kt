package com.spm.androidtesting.account

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import com.spm.androidtesting.R
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules


class AccountActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        loadKoinModules(accountModule)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
    }

    override fun onDestroy() {
        unloadKoinModules(accountModule)
        super.onDestroy()
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun appInResumeState() {
        Toast.makeText(this, "In Foreground", Toast.LENGTH_LONG).show()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun appInPauseState() {
        Toast.makeText(this, "In Background", Toast.LENGTH_LONG).show()
    }
}
