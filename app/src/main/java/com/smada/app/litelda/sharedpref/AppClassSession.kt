package com.smada.app.litelda.sharedpref

import android.app.Application
import android.content.Context

class AppClassSession: Application() {
    companion object{
        lateinit var sessionManager: SessionManager
        private lateinit var context: Context
    }

    override fun onCreate(){
        super.onCreate()
        context = this
        sessionManager = SessionManager(context)
    }
}