package com.ngb.namaztime.data.response


import com.google.gson.annotations.SerializedName

data class Meta(
    val latitude: Double,
    val latitudeAdjustmentMethod: String,
    val longitude: Double,
    val method: Method,
    val timezone: String
)