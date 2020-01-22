package com.ngb.namaztime.data.response


import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class Date(
    @Embedded(prefix = "greg_")
    val gregorian: Gregorian,
    @Embedded(prefix = "hijri_")
    val hijri: Hijri,
    val readable: String,
    val timestamp: String
)