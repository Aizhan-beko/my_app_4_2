package com.geeks.my_app_4_2

import android.app.Application
import com.geeks.my_app_4_2.utils.PreferenceHelper

class App:Application(){

    override fun onCreate() {
        super.onCreate()

        val sharedPreferences = PreferenceHelper(this)
        sharedPreferences.unit(this)
    }

}