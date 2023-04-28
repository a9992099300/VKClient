package com.a9992099300.vkclient.presentation.comments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.a9992099300.vkclient.domain.FeedPost

class CommentsViewModelFactory(
   private val feedPost: FeedPost,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CommentsViewModel(feedPost) as T
    }
}