package com.sib.jenio.models

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by irvan on 9/30/16.
 */
class Transaction {

    var id: String? = null

    var item: Item? = null

    var amount: Int = 0

    @SerializedName("date_time")
    var timestamp: Date? = null
}