package com.ngb.namaztime.data.response

import com.google.gson.annotations.SerializedName
import com.ngb.namaztime.data.db.entities.TodayData



data class TodayResponse(
    val code: Int,
    @SerializedName("data")
    val todayData: TodayData,
    val status: String
)