package com.da62.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class KakaoTalkProfile(
    @SerializedName("nickName") @Expose val nickName: String,
    @SerializedName("profileImageURL") @Expose val profileImageURL: String,
    @SerializedName("thumbnailURL") @Expose val thumbnailURL: String,
    @SerializedName("countryISO") @Expose val countryISO: String
)