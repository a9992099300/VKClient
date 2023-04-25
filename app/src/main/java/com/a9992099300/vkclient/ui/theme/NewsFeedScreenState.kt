package com.a9992099300.vkclient.ui.theme

import com.a9992099300.vkclient.domain.FeedPost

sealed class NewsFeedScreenState {

    object Initial : NewsFeedScreenState()

    data class Posts(val posts: List<FeedPost>) : NewsFeedScreenState()

}