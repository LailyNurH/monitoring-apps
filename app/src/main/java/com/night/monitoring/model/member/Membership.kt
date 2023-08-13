package com.night.monitoring.model.member


import com.google.gson.annotations.SerializedName

data class Membership(
    @SerializedName("alamat")
    val alamat: String,
    @SerializedName("guna_berlangganan")
    val gunaBerlangganan: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("id_user")
    val idUser: String,
    @SerializedName("jenis_berlangganan")
    val jenisBerlangganan: String,
    @SerializedName("jumlah_unit")
    val jumlahUnit: String,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("no_hp")
    val noHp: String,
    @SerializedName("no_ktp")
    val noKtp: String,
    @SerializedName("pekerjaan")
    val pekerjaan: String,
    @SerializedName("pembayaran_muka")
    val pembayaranMuka: String,
    @SerializedName("status_bayar")
    val statusBayar: String,
    @SerializedName("status_verifikasi")
    val statusVerifikasi: String,
    @SerializedName("tanggal_pengajuan")
    val tanggalPengajuan: String,
    @SerializedName("tgl_berlangganan")
    val tglBerlangganan: String,

    @SerializedName("jatuh_tempo")
    val jatuh_tempo: String
)