package com.a9992099300.vkclient.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.a9992099300.vkclient.CommentsViewModel
import com.a9992099300.vkclient.CommentsViewModelFactory
import com.a9992099300.vkclient.domain.FeedPost
import com.a9992099300.vkclient.domain.PostComment

@Composable
fun CommentsScreen(
    onBackPressed: () -> Unit,
    feedPost: FeedPost
) {

    val viewModel: CommentsViewModel = viewModel(
        factory = CommentsViewModelFactory(feedPost)
    )
    val screenState = viewModel.screenState.observeAsState(CommentsScreenState.Initial)
    val currentState = screenState.value

    if (currentState is CommentsScreenState.Comments) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Comment for FeedPost id ${currentState.feedPost.contentText}")
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            onBackPressed()
                        }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = null
                            )

                        }
                    }

                )
            }
        ) { padding ->
            LazyColumn(
                modifier = Modifier.padding(padding),
                contentPadding = PaddingValues(
                    top = 16.dp,
                    start = 8.dp,
                    end = 8.dp,
                    bottom = 72.dp
                )
            ) {
                items(
                    items = currentState.comments,
                    key = { it.id }
                ) { comment ->
                    CommentItem(comment = comment)
                }
            }
        }
    }

}

@Composable
private fun CommentItem(comment: PostComment) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(
                id = comment.authorAvatarId
            ),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column() {
            Text(
                text = comment.authorName + comment.id,
                color = MaterialTheme.colors.onPrimary,
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = comment.commentTest,
                color = MaterialTheme.colors.onPrimary,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = comment.publicationDate,
                color = MaterialTheme.colors.onSecondary,
                fontSize = 12.sp
            )

        }

    }
}

@Preview
@Composable
private fun PreviewItem() {
    VKClientTheme {
        CommentItem(
            comment = PostComment(id = 0)
        )
    }
}