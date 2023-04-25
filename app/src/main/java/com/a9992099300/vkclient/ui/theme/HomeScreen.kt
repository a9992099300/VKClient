package com.a9992099300.vkclient.ui.theme

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissDirection
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.a9992099300.vkclient.NewFeedViewModel
import com.a9992099300.vkclient.domain.FeedPost

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    onCommentClickListener: (FeedPost) -> Unit
) {

    val viewModel: NewFeedViewModel = viewModel()

    val screenState = viewModel.screenState.observeAsState(NewsFeedScreenState.Initial)


    when (val currentState = screenState.value) {
        is NewsFeedScreenState.Posts -> {
            FeedPosts(
                viewModel = viewModel,
                paddingValues = paddingValues,
                posts = currentState.posts,
                onCommentClickListener
            )
        }
//        is NewsFeedScreenState.Comments -> {
//            CommentsScreen(
//                feedPost = currentState.feedPost,
//                comments = currentState.comments,
//                onBackPressed = {
//                    viewModel.closeComments()
//                }
//            )
//            BackHandler {
//                viewModel.closeComments()
//            }
//        }
        NewsFeedScreenState.Initial -> {

        }
    }

}

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
private fun FeedPosts(
    viewModel: NewFeedViewModel,
    paddingValues: PaddingValues,
    posts: List<FeedPost>,
    onCommentClickListener: (FeedPost) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues),
        contentPadding = PaddingValues(
            top = 16.dp,
            start = 8.dp,
            end = 8.dp,
            bottom = 72.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = posts,
            key = { it.id }
        ) { feedPost ->
            val dismissState = rememberDismissState()
            if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                viewModel.remove(feedPost)
            }

            SwipeToDismiss(
                modifier = Modifier.animateItemPlacement(),
                state = dismissState,
                background = {},
                directions = setOf(DismissDirection.EndToStart)
            ) {
                PostCard(
                    modifier = Modifier,
                    feedPost = feedPost,
                    onCommentClick = {
                        onCommentClickListener(feedPost)
                    },
                    onLikeClick = { statistic ->
                        viewModel.updateCount(feedPost, statistic)
                    },
                    onViewClick = { statistic ->
                        viewModel.updateCount(feedPost, statistic)
                    },
                    onShareClick = { statistic ->
                        viewModel.updateCount(feedPost, statistic)
                    },
                )
            }
        }
    }
}