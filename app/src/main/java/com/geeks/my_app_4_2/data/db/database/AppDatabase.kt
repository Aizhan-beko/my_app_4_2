package com.geeks.my_app_4_2.data.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.geeks.my_app_4_2.data.db.daos.NoteDao
import com.geeks.my_app_4_2.data.models.NoteModel
import com.geeks.my_app_4_2.data.models.colorConverter.ColorContainerConverter

@Database(entities = [NoteModel::class], version = 3)
@TypeConverters(ColorContainerConverter::class)

abstract class AppDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDao
}