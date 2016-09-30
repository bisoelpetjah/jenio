package com.cekiboy.ceki.http

import com.cekiboy.ceki.models.Item
import com.cekiboy.ceki.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by irvan on 9/30/16.
 */
interface Services {

    @GET("user/{id}")
    fun getUser(@Path("id") id: String?): Call<User>

    @GET("item/{id}/get")
    fun getItem(@Path("id") id: String?): Call<Item>
}