package com.cekiboy.ceki.utils

import android.content.Context
import android.util.DisplayMetrics

/**
 * Created by irvan on 9/30/16.
 */
object DimensionHelper {

    fun dpToPx(context: Context, dp: Float): Float {
        return dp * (context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
    }

    fun pxToDp(context: Context, px: Float): Float {
        return px / (context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
    }
}