package com.night.monitoring.model.register


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("user")
    val user: User
)