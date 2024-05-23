package com.geeks.my_app_4_2.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper(mainActivity: Context) {
    private lateinit var sharedPreference: SharedPreferences

    init {
        unit(mainActivity)
    }

    fun unit(context: Context){
        sharedPreference = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
    }
    var title: String?
        get() = sharedPreference.getString("title", "")
        set(value) = sharedPreference.edit().putString("title", value)!!.apply()

    var isOnboardShown: Boolean
        get() = sharedPreference.getBoolean("board", false)
        set(value) = sharedPreference.edit().putBoolean("board", value).apply()
}