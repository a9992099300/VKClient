package com.a9992099300.vkclient.domain

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.a9992099300.vkclient.R
import com.google.gson.GsonBuilder
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeedPost(
    val id: Int = 0,
    val communityName: String = "/dev/null",
    val publicationDate: String = "14:00",
    val avatarResId: Int = R.drawable.ic_launcher_background,
    val contentText: String = "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consecrate, adipisci velit...",
    val contentImageId: Int = R.drawable.ic_launcher_background,
    val statistics: List<StatisticItem> = listOf(
        StatisticItem(type = StatisticType.VIEWS, 966),
        StatisticItem(type = StatisticType.SHARES, 7),
        StatisticItem(type = StatisticType.COMMENTS, 8),
        StatisticItem(type = StatisticType.LIKES, 27),
    )

) : Parcelable {

    companion object {
         val navigationType:NavType<FeedPost> = object : NavType<FeedPost>(false) {

             override fun put(bundle: Bundle, key: String, value: FeedPost) {
                 bundle.putParcelable(key, value)
             }

             @Suppress("DEPRECATION")
             override fun get(bundle: Bundle, key: String): FeedPost {
                 return bundle.getParcelable(key) ?: FeedPost()
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