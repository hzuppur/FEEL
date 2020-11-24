package com.example.feel.data

import androidx.lifecycle.LiveData

class FeelingRepository(private val feelingDao: FeelingDao) {

    val readAllData: LiveData<List<Feeling>> = feelingDao.readAllData()

    suspend fun addFeeling(feeling: Feeling){
        feelingDao.addFeeling(feeling)
    }
}