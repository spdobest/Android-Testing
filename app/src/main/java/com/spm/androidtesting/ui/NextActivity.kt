package com.spm.androidtesting.ui

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.spm.androidtesting.R
import com.spm.androidtesting.background.FirebaseMessageReadJobService


class NextActivity : AppCompatActivity() {
    private val JOB_ID = 1001
    private val REFRESH_INTERVAL = 5 * 1000 // 5 seconds
        .toLong()
    val LOAD_ARTWORK_JOB_ID = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)
        scheduleJob()
    }

    fun scheduleJob() {


        var jobInfo: JobInfo
        jobInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            JobInfo.Builder(JOB_ID, ComponentName(this, FirebaseMessageReadJobService::class.java))
                .setMinimumLatency(5000)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setPersisted(true)
                .build()

        } else {
            JobInfo.Builder(JOB_ID, ComponentName(this, FirebaseMessageReadJobService::class.java))
                .setPeriodic(REFRESH_INTERVAL)
                .build()
        }

        val mJobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        val mJobBuilder = JobInfo.Builder(
            123,
            ComponentName(
                packageName,
                FirebaseMessageReadJobService::class.java.name
            )
        )

        mJobBuilder
            .setMinimumLatency(1 * 1000.toLong()) //YOUR_TIME_INTERVAL
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
        mJobScheduler.schedule(jobInfo)


        /* val jobScheduler =
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
         )*/
    }

}
