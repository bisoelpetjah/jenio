package com.cekiboy.ceki.models

import com.google.gson.annotations.SerializedName

/**
 * Created by irvan on 9/30/16.
 */
class User {

    var id: String? = null

    var name: String? = null

    @SerializedName("image")
    var picture: String? = null
}