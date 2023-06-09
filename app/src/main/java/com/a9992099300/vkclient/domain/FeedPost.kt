package com.a9992099300.vkclient.domain

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.google.gson.GsonBuilder
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeedPost(
    val id: Long,
    val communityId: Long,
    val communityName: String,
    val publicationDate: String,
    val communityImageUrl: String,
    val contentText: String,
    val contentImageUrl: String?,
    val statistics: List<StatisticItem>,
    val isLiked: Boolean
) : Parcelable {

    companion object {
         val navigationType:NavType<FeedPost> = object : NavType<FeedPost>(false) {

             override fun put(bundle: Bundle, key: String, value: FeedPost) {
                 bundle.putParcelable(key, value)
             }

             @Suppress("DEPRECATION")
             override fun get(bundle: Bundle, key: String): FeedPost? {
                 return bundle.getParcelable(key)
             }

             override fun parseValue(value: String): FeedPost {
                 val gson = GsonBuilder()
                     .setLenient()
                     .create()
                 return gson.fromJson(value, FeedPost::class.java)
             }
         }
     }


}