package com.ngb.namaztime.data.futureresponse


import com.google.gson.annotations.SerializedName

data class Data(
    val date: Date,
    val meta: Meta,
    val timings: Timings
)