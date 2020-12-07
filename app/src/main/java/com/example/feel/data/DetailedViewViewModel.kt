package com.example.feel.data

import androidx.lifecycle.ViewModel


class DetailedViewViewModel : ViewModel() {
    private lateinit var mFeelingViewModel: FeelingViewModel

    lateinit var feeling: Feeling

    fun addFeeling(feeling: Feeling){
        this.feeling = feeling
    }
}
