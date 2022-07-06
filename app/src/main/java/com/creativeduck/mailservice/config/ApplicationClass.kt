package com.creativeduck.mailservice.config

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationClass : Application() {

    companion object {
        lateinit var applicationClass : ApplicationClass

        fun getInstance() : ApplicationClass {
            return applicationClass
        }
    }
}