package com.spm.androidtesting.background

import android.app.IntentService
import android.app.Service
import android.content.Intent
import android.util.Log

class MessageReadingService : IntentService("MessageReadingService") {

    /**
     * DATABASE
     */
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.i(TAG, "onStartCommand")
        return Service.START_STICKY
    }

    override fun onCreate() {
        Log.i(TAG, "onCreate")
        super.onCreate()
    }

    override fun onHandleIntent(intent: Intent?) {
        Log.i(TAG, "onHandleIntent")
    }

    companion object {
        private const val TAG = "MessageReadingService"
    }
}