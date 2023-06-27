package com.hj.servicetest

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val action = intent?.action
        Log.d("StartedService", "action = $action")
        return super.onStartCommand(intent, flags, startId)
    }

    companion object {
        val ACTION_START = "com.hj.servicetest.START"
        val ACTION_RUN = "com.hj.servicetest.RUN"
        val ACTION_STOP = "com.hj.servicetest.STOP"
    }

    override fun onDestroy() {
        Log.d("Service","서비스가 종료되었습니다.")
        super.onDestroy()
    }
    inner class MyBinder : Binder() {
        fun getService(): MyService {
            return this@MyService
        }
    }

    val binder = MyBinder()
    fun serviceMessage(): String {
        return "Hello Activity! I am Service!"
    }
}