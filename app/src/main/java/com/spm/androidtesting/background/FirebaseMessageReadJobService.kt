package com.spm.androidtesting.background

import android.app.job.JobInfo
import android.app.job.JobParameters
import android.app.job.JobScheduler
import android.app.job.JobService
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import java.util.*

class FirebaseMessageReadJobService : JobService() {

    lateinit var timer: Timer
    lateinit var timerTask: TimerTask
    var oldTime: Long = 0
    private var counter = 0

    override fun onStartJob(jobParameters: JobParameters): Boolean { //Reschedule the Service before calling job finished
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            scheduleRefreshForNaught(applicationContext)
        }


        startTimer()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(
                Intent(
                    applicationContext,
                    MessageReadingService::class.java
                )
            )
        } else {
            startService(Intent(applicationContext, MessageReadingService::class.java))
        }
        return true
    }

    override fun onStopJob(jobParameters: JobParameters): Boolean {
        return false
    }

    fun scheduleJobFirebaseToReadMessage(context: Context) {
        val jobScheduler = context
            .getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        val componentName = ComponentName(
            context,
            FirebaseMessageReadJobService::class.java
        )
        //  86400000     =         24 HOUR
//  900000       =         15 Minute
//  60000       =         1 Minute
        val jobInfo: JobInfo
        jobInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            JobInfo.Builder(JOB_ID, componentName)
                .setBackoffCriteria(
                    6000,
                    JobInfo.BACKOFF_POLICY_LINEAR
                ) //    .setOverrideDeadline(300)
//    .setMinimumLatency(60000)
                .setPeriodic(60000) //                    .setExtras(bundle)
                .build()
        } else {
            JobInfo.Builder(JOB_ID, componentName)
                .setPeriodic(REFRESH_INTERVAL) //                    .setExtras(bundle)
                .build()
        }
        /*  JobInfo jobInfo = new JobInfo.Builder(1, componentName)
                .setPeriodic(60000).setRequiredNetworkType(
                        JobInfo.NETWORK_TYPE_NOT_ROAMING)
                .setRequiresDeviceIdle(false)
                .setRequiresCharging(false)
                .setPersisted(true)
                .setPeriodic(10000)
                .build();*/
        val resultCode = jobScheduler.schedule(jobInfo)
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "Job scheduled!")
        } else {
            Log.d(TAG, "Job not scheduled")
        }
    }

    fun scheduleRefreshForNaught(context: Context) {
        val mJobScheduler = context
            .getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        val mJobBuilder = JobInfo.Builder(
            123,
            ComponentName(
                context.packageName,
                MessageReadingService::class.java.name
            )
        )
        /* For Android N and Upper Versions */if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val componentName = ComponentName(
                context,
                FirebaseMessageReadJobService::class.java
            )
            val jobInfo =
                JobInfo.Builder(JOB_ID, componentName)
                    .setMinimumLatency(REFRESH_INTERVAL) //                    .setExtras(bundle)
                    .build()
            mJobBuilder
                .setMinimumLatency(60 * 1000.toLong()) //YOUR_TIME_INTERVAL
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
            mJobScheduler.schedule(jobInfo)
        }
    }

    companion object {
        private const val TAG = "FirebaseMessageReadJobS"
        private const val JOB_ID = 1001
        private const val REFRESH_INTERVAL = 5 * 1000 // 5 seconds
            .toLong()
    }

    fun initializeTimerTask() {
        Log.i(
            "Service.TAG",
            "initialising TimerTask"
        )
        timerTask =
            object : TimerTask() {
                override fun run() {
                    Log.i("in timer", "in timer ++++  " + counter++)
                }
            }
    }

    fun startTimer() {
        Log.i("Service.TAG", "Starting timer")
        //set a new Timer - if one is already running, cancel it to avoid two running at the same time
        stoptimertask()
        timer = Timer()
        //initialize the TimerTask's job
        initializeTimerTask()
        Log.i("Service.TAG", "Scheduling...")
        //schedule the timer, to wake up every 1 second
        timer.schedule(
            timerTask,
            1000,
            1000
        ) //
    }

    fun stoptimertask() { //stop the timer, if it's not already null
        if (timer != null) {
            timer.cancel()
        }
    }
}