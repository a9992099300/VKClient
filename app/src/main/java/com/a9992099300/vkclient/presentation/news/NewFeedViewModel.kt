package com.a9992099300.vkclient.presentation.news

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.a9992099300.vkclient.data.repository.NewsFeedRepository
import com.a9992099300.vkclient.domain.FeedPost
import com.a9992099300.vkclient.domain.StatisticItem
import kotlinx.coroutines.launch

class NewFeedViewModel(application: Application) : AndroidViewModel(application)  {


    private val initialState = NewsFeedScreenState.Initial

    private val _screenState= MutableLiveData<NewsFeedScreenState>(initialState)
    val screenState: LiveData<NewsFeedScreenState> = _screenState

    private val repository = NewsFeedRepository(application)

    init {
        loadRecommendation()
    }

    private fun loadRecommendation() {
        viewModelScope.launch {
            val feedPost = repository.loadRecommendation()
            _screenState.value = NewsFeedScreenState.Posts(
                posts = feedPost
            )
        }
    }
    fun loadNextRecommendation(){
        _screenState.value = NewsFeedScreenState.Posts(
            posts = repository.feedPosts,
            nextDataIsLoading = true
        )
            loadRecommendation()
    }

    fun changeLikeStatus(feedPost: FeedPost) {
        viewModelScope.launch {
            repository.changeLikeStatus(feedPost = feedPost)
            _screenState.value = NewsFeedScreenState.Posts(repository.feedPosts)
        }
    }

    fun updateCount(feedPost: FeedPost, item: StatisticItem) {
        val currentState = screenState.value
        if(currentState !is NewsFeedScreenState.Posts) return

        val oldPost = currentState.posts.toMutableList()
        val oldStatistics = feedPost.statistics
        val newStatistics = oldStatistics.toMutableList().apply {
            replaceAll { oldItem ->
                if(oldItem.type == item.type){
                    oldItem.copy(count =  oldItem.count + 1)
                } else {
                    oldItem
                }
            }
        }

        val newFeedPost = feedPost.copy(statistics = newStatistics)

        val newPosts = oldPost.apply {
            replaceAll {
                if (it.id == newFeedPost.id) {
                    newFeedPost
                } else {
                    it
                }
            }
        }
        _screenState.value = NewsFeedScreenState.Posts(newPosts)
    }

    fun remove(feedPost: FeedPost) {
        val currentState = screenState.value
        if(currentState !is NewsFeedScreenState.Posts) return
        val oldPost = currentState.posts.toMutableList()
        oldPost.remove(feedPost)
        _screenState.value = NewsFeedScreenState.Posts(oldPost)
    }
}