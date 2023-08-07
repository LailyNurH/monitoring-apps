package com.night.monitoring.model.member

import com.google.gson.annotations.SerializedName

data class MemberResponsePost(
    @SerializedName("data")
    val `data`: DataMember,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)

data class DataMember(
    @SerializedName("member")
    val member: Membership
)