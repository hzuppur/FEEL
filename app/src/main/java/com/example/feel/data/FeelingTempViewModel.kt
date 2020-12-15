package com.example.feel.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

class FeelingTempViewModel : ViewModel() {
    private lateinit var mFeelingViewModel: FeelingViewModel

    var feelingPositivity: Int? = null
    var feelingIntensity: Int? = null
    var feelingLocation: List<FeelingSpot> = mutableListOf()
    var trigger: String? = null
    var feelingType: String? = null
    var reactionToFeeling: String? = null
    var feelingTime: Long? = null

    lateinit var feeling: Feeling

    fun addToDatabase(owner: ViewModelStoreOwner) {
        if (
            feelingPositivity != null &&
            feelingIntensity != null &&
            feelingLocation.isNotEmpty() &&
            trigger != null &&
            feelingType != null &&
            reactionToFeeling != null &&
            feelingTime != null
        ){
            mFeelingViewModel = ViewModelProvider(owner).get(FeelingViewModel::class.java)

            feeling = Feeling(
                0,
                feelingPositivity!!,
                feelingIntensity!!,
                feelingLocation,
                trigger!!,
                feelingType!!,
                reactionToFeeling!!,
                feelingTime!!
            )

            mFeelingViewModel.addFeeling(feeling)
        }
    }

    override fun toString(): String {
        return "FeelingTempViewModel(feelingPositivity=$feelingPositivity, feelingIntensity=$feelingIntensity, feelingLocation=$feelingLocation, trigger=$trigger, feelingType=$feelingType, reactionToFeeling=$reactionToFeeling, feelingTime=$feelingTime)"
    }


}