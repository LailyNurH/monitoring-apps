package com.night.monitoring.api

import com.google.gson.annotations.SerializedName
import com.night.monitoring.model.LoginResponse
import com.night.monitoring.model.MemberResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiEndpoint {
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") user_email : String,
        @Field("password") password : String
    ) : Call<LoginResponse>

    @FormUrlEncoded
    @POST("member")
    fun membership(
        @Field ("alamat") alamat: String,
        @Field ("id_user") id_user: String,
        @Field ("instansi") instansi: String,
        @Field ("nama") nama: String,
        @Field ("no_hp") no_hp: String,
        @Field ("no_ktp") no_ktp: String,
        @Field ("pekerjaan") pekerjaan: String,
    ) : Call<MemberResponse>


}