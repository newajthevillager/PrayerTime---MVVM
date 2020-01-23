package com.ngb.namaztime.data.futureresponse


import com.google.gson.annotations.SerializedName

data class Date(
    val gregorian: Gregorian,
    val hijri: Hijri,
    val readable: String,
    val timestamp: String
)