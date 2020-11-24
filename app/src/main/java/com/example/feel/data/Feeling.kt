package com.example.feel.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Entity(tableName = "feeling_table")
data class Feeling(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val feeling_positivity: Int,

    val feeling_intensity: Int,

    @TypeConverters(CategoryConverter::class)
    val feeling_location: FeelingSpots?,

    val trigger: String,

    val feeling_type: String,

    val reaction_to_feeling: String,

    val feeling_time: Int
)

data class FeelingSpots(
    val feeling_location: List<FeelingSpot> = ArrayList()
)

class CategoryConverter {
    @TypeConverter
    fun toCategories(value: String?): FeelingSpots {
        if (value == null || value.isEmpty()) {
            return FeelingSpots()
        }

        val list: List<String> = value.split(",")
        val feelingSpotList = ArrayList<FeelingSpot>()
        for (item in list) {
            if (!item.isEmpty()) {
                val pos = item.split(":")
                feelingSpotList.add(FeelingSpot(pos[0].toFloat(), pos[1].toFloat()))
            }
        }
        return FeelingSpots(feelingSpotList)
    }

    @TypeConverter
    fun toString(feeling_location: FeelingSpots?): String {

        var string = ""

        if (feeling_location == null) {
            return string
        }

        feeling_location.feeling_location.forEach {
            string += "${it.x}:${it.y},"
        }
        return string
    }
}