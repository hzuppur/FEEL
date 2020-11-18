package com.example.feel

//creating a Data Model Class
class EmpModelClass(val id: Int,
                    val feeling_positivity: Int,
                    val feeling_intensity: Int,
                    val feeling_location: ByteArray,//need to convert List<FeelingSpot> to ByteArray
                    val trigger: String,
                    val feeling_type: String,
                    val reaction_to_feeling: String,
                    val feeling_time: Int
)