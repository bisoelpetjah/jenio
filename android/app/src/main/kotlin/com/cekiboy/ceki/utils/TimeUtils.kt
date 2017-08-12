package com.cekiboy.ceki.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by irvan on 9/30/16.
 */
object TimeUtils {

    fun getRelativeTimeSpan(date: Date?): String {
        val now = Calendar.getInstance()
        val then = Calendar.getInstance()

        now.time = Date()
        then.time = date

        val nowMs = now.timeInMillis
        val thenMs = then.timeInMillis

        val diff = nowMs - thenMs

        val diffSeconds = diff / 1000
        val diffMinutes = diff / (60 * 1000)
        val diffHours = diff / (60 * 60 * 1000)
        val diffDays = diff / (24 * 60 * 60 * 1000)

        if (diffSeconds < 60) {
            return "$diffSeconds detik"
        } else if (diffMinutes < 60) {
            return "$diffMinutes menit"
        } else if (diffHours < 24) {
            return "$diffHours jam"
        } else if (diffDays < 7) {
            return "$diffDays hari"
        } else {
            return SimpleDateFormat("dd MMM", Locale("id")).format(date)
        }
    }
}