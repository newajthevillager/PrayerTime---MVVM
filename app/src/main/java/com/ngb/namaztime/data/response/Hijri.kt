package com.ngb.namaztime.data.response


import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class Hijri(
    val date: String,
    val day: String,
    @Embedded(prefix = "destix_")
    val designation: DesignationX,
    val format: String,
//    val holidays: List<Any>,
    @Embedded(prefix = "monthx_")
    val month: MonthX,
    @Embedded(prefix = "weekdayx_")
    val weekday: WeekdayX,
    val year: String
)