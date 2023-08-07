package com.night.monitoring.model.member


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("membership")
    val membership: List<Membership>
)