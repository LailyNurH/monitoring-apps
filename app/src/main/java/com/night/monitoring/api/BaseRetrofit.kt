package com.night.monitoring.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BaseRetrofit {
    val endpoint : ApiEndpoint
        get(){
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://tvkabelmimika.000webhostapp.com/index.php/Api_pcs/")
//                .baseUrl("http://192.168.12.24/monitoring/index.php/Api_pcs/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiEndpoint::class.java)
        }
}