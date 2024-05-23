package com.geeks.my_app_4_2.data.models.colorConverter

import androidx.room.TypeConverter

class ColorContainerConverter {
    @TypeConverter

    fun fromColorContainer(value: String): ColorContainer {
        return when (value) {
            "dark_grey" -> ColorContainer.DARK_GREY
            "creamy" -> ColorContainer.CREAMY
            "dark_red" -> ColorContainer.DARK_RED
            else -> ColorContainer.UNKNOWN
        }
    }

    @TypeConverter
    fun toColorContainer(colorContainer: ColorContainer): String {
        return colorContainer.name.lowercase()
    }
}
