package com.ngb.namaztime.data.futureresponse


import com.google.gson.annotations.SerializedName

data class Method(
    val id: Int,
    val name: String,
    val params: Params
)