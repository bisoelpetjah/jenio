package com.cekiboy.ceki

import android.app.Application
import com.cekiboy.ceki.http.WebService

/**
 * Created by irvan on 9/30/16.
 */
class Application: Application() {
    override fun onCreate() {
        super.onCreate()

        WebService.init()
    }
}