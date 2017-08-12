package com.cekiboy.ceki

import android.app.Application
import com.cekiboy.ceki.http.WebService
import com.cekiboy.ceki.utils.PreferencesHelper

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