package com.a9992099300.vkclient.data.model

import com.google.gson.annotations.SerializedName

data class LikesDto(
   @SerializedName("count") val count: Int,
   @SerializedName("user_likes") val userLikes: Int
)
