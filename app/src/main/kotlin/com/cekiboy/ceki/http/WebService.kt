package com.cekiboy.ceki.http

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by irvan on 9/30/16.
 */
object WebService {

    private val BASE_URL = "https://staging.sistemsista.com/"

    var services: Services? = null
        private set

    fun init() {
        val gson = GsonBuilder().create()
        val converter = GsonConverterFactory.create(gson)

        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

        var retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(converter)
                .client(httpClient)
                .build()

        services = retrofit.create(Services::class.java)
    }
}