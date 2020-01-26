package com.ngb.namaztime.data.provider

interface LocationProvider {

    suspend fun getPreferredCity(): String
    suspend fun getPreferredCountry(): String

}