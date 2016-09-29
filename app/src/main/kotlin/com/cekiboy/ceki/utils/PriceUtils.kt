package com.cekiboy.ceki.utils

import java.text.NumberFormat
import java.util.*

/**
 * Created by irvan on 9/29/16.
 */
object PriceUtils {

    fun formatNumberToPrice(number: Int): String {
        return NumberFormat.getInstance(Locale("id")).format(number)
    }

    fun formatNumberToRupiah(number: Int): String {
        return "Rp${formatNumberToPrice(number)},00"
    }
}