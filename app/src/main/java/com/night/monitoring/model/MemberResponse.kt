package com.night.monitoring.model


import com.google.gson.annotations.SerializedName

data class MemberResponse(
    @SerializedName("data")
    val `data`: DataX,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)