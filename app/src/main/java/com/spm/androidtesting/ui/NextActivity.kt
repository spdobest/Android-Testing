package com.spm.androidtesting.ui

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.spm.androidtesting.R
import com.spm.androidtesting.account.restarter.RestartServiceBroadcastReceiver
import com.spm.androidtesting.background.FirebaseMessageReadJobService


class NextActivity : AppCompatActivity() {

    val LOAD_ARTWORK_JOB_ID = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)
//        scheduleJob()
    }

    override fun onResume() {
        super.onResume()
            RestartServiceBroadcastReceiver.scheduleJob(applicationContext)
        scheduleJob()
    }

    fun scheduleJob() {
        val jobScheduler =
            getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        jobScheduler.schedule(
            JobInfo.Builder(
                LOAD_ARTWORK_JOB_ID,
                ComponentName(this, FirebaseMessageReadJobService::class.java)
            )
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setOverrideDeadline(0)// time delay to start the ser
                .setPeriodic(0)
                .setPersisted(true)
                .build()
        )
    }

}
