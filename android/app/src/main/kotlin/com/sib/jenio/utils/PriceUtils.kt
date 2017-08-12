package com.sib.jenio.utils

import java.text.NumberFormat
import java.util.*

/**
 * Created by irvan on 9/29/16.
 */
object PriceUtils {

    fun formatNumberToPrice(number: Long): String {
        return NumberFormat.getInstance(Locale("id")).format(number)
    }

    fun formatNumberToRupiah(number: Long): String {
        return "Rp${formatNumberToPrice(number)},00"
    }
}