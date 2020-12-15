package com.example.feel.data

import androidx.lifecycle.ViewModel


class DetailedViewViewModel : ViewModel() {
    lateinit var feeling: Feeling

    fun addFeeling(feeling: Feeling){
        this.feeling = feeling
    }
}
