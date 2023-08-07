package com.night.monitoring.model.member


import com.google.gson.annotations.SerializedName

data class MemberResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)