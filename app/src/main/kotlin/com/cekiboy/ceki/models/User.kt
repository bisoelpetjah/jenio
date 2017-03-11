package com.cekiboy.ceki.models

import com.google.gson.annotations.SerializedName

/**
 * Created by irvan on 9/30/16.
 */
class User {

    var id: Int = 0

    var name: String? = null

    var email: String? = null

    @SerializedName("image")
    var picture: String? = null

    var balance: Long = 0
}