package com.example.feel.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FeelingViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Feeling>>
    private val repository: FeelingRepository

    init {
        val feelingDao = FeelingDatabase.getDatabase(application).feelingDao()
        repository = FeelingRepository(feelingDao)
        readAllData = repository.readAllData
    }

    fun addFeeling(feeling: Feeling){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFeeling(feeling)
        }
    }
}