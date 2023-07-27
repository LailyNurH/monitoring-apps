package com.night.monitoring.model


import com.google.gson.annotations.SerializedName

data class Member(
    @SerializedName("alamat")
    val alamat: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("id_user")
    val idUser: String,
    @SerializedName("instansi")
    val instansi: String,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("no_hp")
    val noHp: String,
    @SerializedName("no_ktp")
    val noKtp: String,
    @SerializedName("pekerjaan")
    val pekerjaan: String
)