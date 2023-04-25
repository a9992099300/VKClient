package com.a9992099300.vkclient.navigation

import android.net.Uri
import com.a9992099300.vkclient.domain.FeedPost
import com.google.gson.Gson

sealed class Screen (
    val route: String
    ) {
    object Favourite: Screen(ROUTE_FAVOURITE)
    object Profile: Screen(ROUTE_PROFILE)
    object Home: Screen(ROUTE_HOME_FEET)
    object NewsFeed: Screen(ROUTE_NEWS_FEET)
    object Comments: Screen(ROUTE_COMMENTS) {

        private const val ROUTE_FOR_ARGS = "comments"

        fun getRouteWithArgs(feedPost: FeedPost) : String {
            val feedPostJson = Gson().toJson(feedPost)
            return "$ROUTE_FOR_ARGS/${feedPostJson.encode()}"
        }
    }

   companion object{
        const val KEY_FEED_POST = "feed_post"
        const val ROUTE_HOME_FEET = "home"
        const val ROUTE_COMMENTS = "comments/{$KEY_FEED_POST}"
        const val ROUTE_NEWS_FEET = "news_feet"
        const val ROUTE_FAVOURITE = "favourite"
        const val ROUTE_PROFILE = "profile"
    }
}

fun String.encode() : String {
    return Uri.encode(this)
}