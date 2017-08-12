package com.sib.jenio.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

/**
 * Created by irvan on 9/30/16.
 */
object PreferencesHelper {

    private val PREF_USER_ID = "pref_user_id"

    private var preferences: SharedPreferences? = null

    var context: Context? = null
        set(value) {
            field = value
            preferences = PreferenceManager.getDefaultSharedPreferences(context)
        }

    var userId: String?
        get() = preferences?.getString(PREF_USER_ID, null)
        set(value) {
            if (value == null) {
                preferences?.edit()
                        ?.remove(PREF_USER_ID)
                        ?.apply()
            } else {
                preferences?.edit()
                        ?.putString(PREF_USER_ID, value)
                        ?.apply()
            }
        }
}