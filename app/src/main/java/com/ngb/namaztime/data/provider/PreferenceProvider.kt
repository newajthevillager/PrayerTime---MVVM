package com.ngb.namaztime.data.provider

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

abstract class PreferenceProvider(var context: Context) {

    protected val preferences: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(context.applicationContext)

}