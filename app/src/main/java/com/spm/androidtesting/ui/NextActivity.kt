package com.spm.androidtesting.ui

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.spm.androidtesting.R
import com.spm.androidtesting.account.restarter.ProcessMainClass
import com.spm.androidtesting.account.restarter.RestartServiceBroadcastReceiver


class NextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)
    }

    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            RestartServiceBroadcastReceiver.scheduleJob(applicationContext)
        } else {
            val bck = ProcessMainClass()
            bck.launchService(applicationContext)
        }
    }
}
