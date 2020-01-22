package com.ngb.namaztime.data.response


import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class Gregorian(
    val date: String,
    val day: String,
    @Embedded(prefix = "destination_")
    val designation: Designation,
    val format: String,
    @Embedded(prefix = "month_")
    val month: Month,
    @Embedded(prefix = "weekday_")
    val weekday: Weekday,
    val year: String
)