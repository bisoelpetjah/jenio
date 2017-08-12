package com.sib.jenio

import android.app.Application
import com.sib.jenio.http.WebService
import com.sib.jenio.utils.PreferencesHelper

/**
 * Created by irvan on 9/30/16.
 */
class Application: Application() {
    override fun onCreate() {
        super.onCreate()

        PreferencesHelper.context = this

        WebService.init()
    }
}