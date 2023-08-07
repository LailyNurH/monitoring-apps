package com.night.monitoring.model


import com.google.gson.annotations.SerializedName
import com.night.monitoring.model.member.Membership

data class DataX(
    @SerializedName("membership")
    val membership: Membership
)