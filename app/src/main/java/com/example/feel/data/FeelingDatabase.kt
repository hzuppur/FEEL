package com.example.feel.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Feeling::class], version = 1, exportSchema = false)
@TypeConverters(CategoryConverter::class)
abstract class FeelingDatabase: RoomDatabase() {

    abstract fun feelingDao(): FeelingDao

    companion object{
        @Volatile
        private var INSTANCE: FeelingDatabase? = null

        fun getDatabase(context: Context): FeelingDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FeelingDatabase::class.java,
                    "feeling_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}