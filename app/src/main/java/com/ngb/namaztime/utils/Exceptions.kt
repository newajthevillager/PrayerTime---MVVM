package com.ngb.namaztime.utils

import java.io.IOException
import java.lang.Exception

class NoInternetConnectionException : IOException()

class LocationPermissionNotGrantedException : Exception()