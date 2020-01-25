package com.ngb.namaztime.data.provider

interface LocationProvider {

    suspend fun getPreferredLocation(): String

}