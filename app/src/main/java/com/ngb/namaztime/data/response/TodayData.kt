package com.ngb.namaztime.data.response


import com.google.gson.annotations.SerializedName

data class TodayData(
    val date: Date,
    val meta: Meta,
    val timings: Timings
)