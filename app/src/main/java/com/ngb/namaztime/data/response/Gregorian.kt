package com.ngb.namaztime.data.response


import com.google.gson.annotations.SerializedName

data class Gregorian(
    val date: String,
    val day: String,
    val designation: Designation,
    val format: String,
    val month: Month,
    val weekday: Weekday,
    val year: String
)