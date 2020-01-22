package com.ngb.namaztime.data.response


import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class Meta(
    val latitude: Double,
    val latitudeAdjustmentMethod: String,
    val longitude: Double,
    @Embedded(prefix = "method_")
    val method: Method,
    val timezone: String
)