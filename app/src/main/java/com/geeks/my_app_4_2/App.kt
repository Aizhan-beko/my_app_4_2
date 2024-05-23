package com.geeks.my_app_4_2

import android.app.Application
import androidx.room.Room
import com.geeks.my_app_4_2.data.db.database.AppDatabase
import com.geeks.my_app_4_2.utils.PreferenceHelper

class App:Application() {

    companion object {
        var appDatabase:AppDatabase?=null

    }
   override fun onCreate() {
        super.onCreate()
       val sharedPreferences = PreferenceHelper(this)
        sharedPreferences.unit(this)
       getInstance()
    }

     fun getInstance(): AppDatabase? {
        if(appDatabase==null){
            appDatabase = applicationContext?.let{
                Room.databaseBuilder(
                    it,
                    AppDatabase:: class.java,
                    "note.database"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
        }
        return appDatabase
    }
    }


