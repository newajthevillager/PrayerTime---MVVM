package com.ngb.namaztime.data.response

import com.google.gson.annotations.SerializedName


data class TodayResponse(
    val code: Int,
    @SerializedName("data")
    val todayData: TodayData,
    val status: String
)