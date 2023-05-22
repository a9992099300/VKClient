package com.a9992099300.vkclient.data.model

import com.google.gson.annotations.SerializedName

data class PhotoDto(
    @SerializedName("sizes")
    val photoUrls: List<PhotoUrlDto>
)
