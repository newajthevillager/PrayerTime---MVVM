package com.ngb.namaztime.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.ngb.namaztime.utils.NoInternetConnectionException
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptorImpl(
    val context: Context
) : ConnectivityInterceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!hasInternetConnection()) {
            throw NoInternetConnectionException()
        }
        return chain.proceed(chain.request())
    }

    private fun hasInternetConnection() : Boolean {
        val connectivityManager = context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

}