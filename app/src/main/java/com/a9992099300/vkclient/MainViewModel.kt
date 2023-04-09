package com.a9992099300.vkclient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.a9992099300.vkclient.domain.FeedPost
import com.a9992099300.vkclient.domain.StatisticItem

class MainViewModel: ViewModel() {

    private val _feedPost= MutableLiveData<FeedPost>(FeedPost())
    val feedPost: LiveData<FeedPost> = _feedPost

    fun updateCount(item: StatisticItem) {
        val oldStatistics = feedPost.value?.statistics ?: throw IllegalStateException()
        val newStatistics = oldStatistics.toMutableList().apply {
            replaceAll { oldItem ->
                if(oldItem.type == item.type){
                    oldItem.copy(count =  oldItem.count + 1)
                } else {
                    oldItem
                }
            }
        }

        _feedPost.value = feedPost.value?.copy(statistics = newStatistics)
    }
}