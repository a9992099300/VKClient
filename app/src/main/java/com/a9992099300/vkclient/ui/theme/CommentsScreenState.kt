package com.a9992099300.vkclient.ui.theme

import com.a9992099300.vkclient.domain.FeedPost
import com.a9992099300.vkclient.domain.PostComment

sealed class CommentsScreenState {

    object Initial : CommentsScreenState()

    data class Comments(
        val feedPost: FeedPost,
        val comments: List<PostComment>
        ) : CommentsScreenState()
}