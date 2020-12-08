package com.example.feel.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Entity(tableName = "feeling_table")
data class Feeling(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val feeling_positivity: Int,

    val feeling_intensity: Int,


    @TypeConverters(CategoryConverter::class)
    val feeling_location: List<FeelingSpot>,

    val trigger: String,

    val feeling_type: String,

    val reaction_to_feeling: String,

    val feeling_time: Long
)

class CategoryConverter {
    @TypeConverter
    fun toCategories(value: String?): List<FeelingSpot> {
        if (value == null || value.isEmpty()) {
            return listOf<FeelingSpot>()
        }

        val list: List<String> = value.split(",")
        val feelingSpotList = ArrayList<FeelingSpot>()
        for (item in list) {
            if (item.isNotEmpty()) {
                val pos = item.split(":")
                feelingSpotList.add(FeelingSpot(pos[0].toFloat(), pos[1].toFloat()))
            }
        }
        return feelingSpotList
    }

    @TypeConverter
    fun toString(feeling_location: List<FeelingSpot>?): String {

        var string = ""

        if (feeling_location == null) {
            return string
        }

        feeling_location.forEach {
            string += "${it.x}:${it.y},"
        }
        return string
    }
}