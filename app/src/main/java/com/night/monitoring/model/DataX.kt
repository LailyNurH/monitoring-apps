package com.night.monitoring.model


import com.google.gson.annotations.SerializedName

data class DataX(
    @SerializedName("member")
    val member: Member
)