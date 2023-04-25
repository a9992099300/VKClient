package com.a9992099300.vkclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.a9992099300.vkclient.domain.FeedPost
import com.a9992099300.vkclient.navigation.Screen.Companion.KEY_FEED_POST


fun NavGraphBuilder.homeScreenNavGraph(
    newFeedScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable (FeedPost) -> Unit
) {
    navigation(
        startDestination = Screen.NewsFeed.route,
        route = Screen.Home.route
    ) {
        composable(Screen.NewsFeed.route) {
            newFeedScreenContent()
        }
        composable(
            route = Screen.Comments.route,
            arguments = listOf(
                navArgument(KEY_FEED_POST) {
                    type = FeedPost.navigationType
                },
            )
        ) {
//            val gson = GsonBuilder()
//                .setLenient()
//                .create()
//            val feedPostJson = it.arguments?.getString(KEY_FEED_POST) ?: ""
//            Log.d("12345", "feedPostJson $feedPostJson")

            val feedPost = it.arguments?.getParcelable<FeedPost>(KEY_FEED_POST) ?: throw RuntimeException("Args is null")
            commentsScreenContent(
                feedPost
            )
        }
    }
}