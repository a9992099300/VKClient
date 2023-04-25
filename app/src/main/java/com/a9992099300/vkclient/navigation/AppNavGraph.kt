package com.a9992099300.vkclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.a9992099300.vkclient.domain.FeedPost

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    newFeedScreenContent: @Composable () -> Unit,
    favoriteScreenContent: @Composable () -> Unit,
    profileScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable (FeedPost) -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route,
        builder = {
            homeScreenNavGraph(
                newFeedScreenContent = newFeedScreenContent,
                commentsScreenContent = commentsScreenContent
            )
            composable(Screen.Favourite.route) {
                favoriteScreenContent()
            }
            composable(Screen.Profile.route) {
                profileScreenContent()
            }
        })
}