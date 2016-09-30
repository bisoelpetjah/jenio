package com.cekiboy.ceki.http

import com.cekiboy.ceki.models.Item
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by irvan on 9/30/16.
 */
interface Services {

    @GET("item/{id}/get")
    fun getItem(@Path("id") id: String?): Call<Item>
}