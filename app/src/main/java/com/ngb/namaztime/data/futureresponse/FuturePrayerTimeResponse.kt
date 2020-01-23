package com.ngb.namaztime.data.futureresponse


import com.google.gson.annotations.SerializedName

data class FuturePrayerTimeResponse(
    val code: Int,
    val `data`: List<Data>,
    val status: String
)