package com.da62.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class KakaoProfile(
    @SerializedName("id") @Expose val id: Int,
    @SerializedName("properties") @Expose val properties: Properties)

data class Properties(
    @SerializedName("nickname") @Expose val nickname: String)