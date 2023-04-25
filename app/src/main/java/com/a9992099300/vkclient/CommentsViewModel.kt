package com.a9992099300.vkclient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.a9992099300.vkclient.domain.FeedPost
import com.a9992099300.vkclient.domain.PostComment
import com.a9992099300.vkclient.ui.theme.CommentsScreenState

class CommentsViewModel(
    feedPost: FeedPost
): ViewModel() {

    private val _screenState= MutableLiveData<CommentsScreenState>(CommentsScreenState.Initial)
    val screenState: LiveData<CommentsScreenState> = _screenState

    init {
        loadComments(feedPost)
    }

        fun loadComments(feedPost: FeedPost) {
          val comments = mutableListOf<PostComment>().apply {
        repeat(20) {
            add(PostComment(id = it))
        }
    }
        _screenState.value = CommentsScreenState.Comments(
            feedPost = feedPost,
            comments = comments
        )
    }


//    fun closeComments() {
//        _screenState.value = savedState
//    }

}