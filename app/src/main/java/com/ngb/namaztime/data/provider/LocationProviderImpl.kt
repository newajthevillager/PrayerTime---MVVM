package com.ngb.namaztime.data.provider

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.ngb.namaztime.R
import com.ngb.namaztime.utils.LocationPermissionNotGrantedException
import com.ngb.namaztime.utils.asDeferred
import kotlinx.coroutines.Deferred


class LocationProviderImpl(
    context: Context,
    private val fusedLocationProviderClient: FusedLocationProviderClient
) : PreferenceProvider(context), LocationProvider {

    private val appContext = context.applicationContext

    // TODO required similar things for country
    // just like getCustomLocation, e.g getCustomCountry
    override suspend fun getPreferredLocation(): String {
        if (isUsingDeviceLocation()) {
            try {
                val deviceLocation = getLastDeviceLocation().await()
                return "${deviceLocation.latitude}, ${deviceLocation.longitude}"
            } catch (e: LocationPermissionNotGrantedException) {
                return getCustomLocation()
            }
        } else {
            return getCustomLocation()
        }
    }

    private fun hasLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            appContext,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun getCustomLocation(): String {
        return preferences.getString(
            context.resources.getString(R.string.customLocation),
            "Chittagong"
        )
    }

    private fun isUsingDeviceLocation(): Boolean {
        return preferences.getBoolean(context.resources.getString(R.string.useDeviceLocation), true)
    }


    // TODO make return type as deferred
    private fun getLastDeviceLocation(): Deferred<Location> {
        if (hasLocationPermission()) {
            return fusedLocationProviderClient.lastLocation.asDeferred()
        } else {
            throw LocationPermissionNotGrantedException()
        }
    }

}