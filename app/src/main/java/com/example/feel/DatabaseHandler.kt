package com.example.feel

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//creating the database logic, extending the SQLiteOpenHelper base class
class DatabaseHandler(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "FeelDatabase"

        private const val TABLE_FEELINGS = "FeelingsTable"

        private const val KEY_ID = "_id"
        private const val KEY_POSITIVITY = "feeling_positivity"
        private const val KEY_INTENSITY = "feeling_intensity"
        private const val KEY_LOCATION = "feeling_location"
        private const val KEY_TRIGGER = "trigger"
        private const val KEY_TYPE = "feeling_type"
        private const val KEY_REACTION = "reaction_to_feeling"
        private const val KEY_TIME = "feeling_time"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        //creating table with fields
        val CREATE_FEELINGS_TABLE = ("CREATE TABLE " + TABLE_FEELINGS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_POSITIVITY + " INTEGER," +
                KEY_INTENSITY + " INTEGER" +
                KEY_LOCATION + " BLOB" +
                KEY_TRIGGER + " TEXT" +
                KEY_TYPE + " TEXT" +
                KEY_REACTION + " TEXT" +
                KEY_TIME + " INTEGER" +
                ")")
        db?.execSQL(CREATE_FEELINGS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_FEELINGS")
        onCreate(db)
    }

    fun addFeeling(emp: EmpModelClass): Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(KEY_POSITIVITY, emp.feeling_positivity)
        contentValues.put(KEY_INTENSITY, emp.feeling_intensity)
        contentValues.put(KEY_LOCATION, emp.feeling_location)
        contentValues.put(KEY_TRIGGER, emp.trigger)
        contentValues.put(KEY_TYPE, emp.feeling_type)
        contentValues.put(KEY_REACTION, emp.reaction_to_feeling)
        contentValues.put(KEY_TIME, emp.feeling_time)

        // Inserting employee details using insert query.
        val success = db.insert(TABLE_FEELINGS, null, contentValues)
        //2nd argument is String containing nullColumnHack

        db.close() // Closing database connection
        return success
    }

    fun updateFeeling(emp: EmpModelClass): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_POSITIVITY, emp.feeling_positivity)
        contentValues.put(KEY_INTENSITY, emp.feeling_intensity)
        contentValues.put(KEY_LOCATION, emp.feeling_location)
        contentValues.put(KEY_TRIGGER, emp.trigger)
        contentValues.put(KEY_TYPE, emp.feeling_type)
        contentValues.put(KEY_REACTION, emp.reaction_to_feeling)
        contentValues.put(KEY_TIME, emp.feeling_time)

        // Updating Row
        val success = db.update(TABLE_FEELINGS, contentValues, KEY_ID + "=" + emp.id, null)
        //2nd argument is String containing nullColumnHack

        // Closing database connection
        db.close()
        return success
    }

    fun deleteFeeling(emp: EmpModelClass): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, emp.id) // EmpModelClass id
        // Deleting Row
        val success = db.delete(TABLE_FEELINGS, KEY_ID + "=" + emp.id, null)
        //2nd argument is String containing nullColumnHack

        // Closing database connection
        db.close()
        return success
    }
}

