package com.geeks.my_app_4_2.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.geeks.my_app_4_2.data.models.colorConverter.ColorContainer
import com.geeks.my_app_4_2.data.models.colorConverter.ColorContainerConverter

@TypeConverters(ColorContainerConverter::class)
@Entity(tableName = "noteModel")

data class NoteModel(
    val title: String,
    val description: String,
    val time: String,
    val date: String,
    val color:String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}