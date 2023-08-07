package com.night.monitoring.api

import com.night.monitoring.model.LoginResponse
import com.night.monitoring.model.MemberResponse
import com.night.monitoring.model.member.MemberResponsePost
import retrofit2.Call
import retrofit2.http.*

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
        @Field ("nama") nama: String,
        @Field ("no_hp") no_hp: String,
        @Field ("no_ktp") no_ktp: String,
        @Field ("pekerjaan") pekerjaan: String,
        @Field ("jenis_berlangganan") jenis_berlangganan: String,
        @Field ("guna_berlangganan") guna_berlangganan: String,
        @Field ("pembayaran_muka") pembayaran_muka: String,
        @Field ("tgl_berlangganan") tgl_berlangganan: String,
        @Field ("jumlah_unit") jumlah_unit: String,
        @Field ("status_verifikasi") status_verifikasi: String,
        @Field ("status_bayar") status_bayar: String,
        @Field ("tanggal_pengajuan") tanggal_pengajuan: String
    ) : Call<MemberResponsePost>

    @GET("member/{id}")
    fun  getPengajuan(
        @Path("id") id: Int,
    ) : Call <com.night.monitoring.model.member.MemberResponse>

    @GET("detailmember/{id}")
    fun  getDetailMember(
        @Path("id") id: Int,
    ) : Call <com.night.monitoring.model.MemberResponse>


}