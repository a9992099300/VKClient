package com.a9992099300.vkclient.presentation.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.a9992099300.vkclient.R
import com.a9992099300.vkclient.domain.FeedPost
import com.a9992099300.vkclient.domain.StatisticItem
import com.a9992099300.vkclient.domain.StatisticType


@Composable
fun PostCard(
    modifier: Modifier,
    feedPost: FeedPost,
    onLikeClick:(StatisticItem) -> Unit,
    onShareClick:(StatisticItem) -> Unit,
    onViewClick:(StatisticItem) -> Unit,
    onCommentClick:(StatisticItem) -> Unit,
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            PostHeader(feedPost)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = feedPost.contentText,
                color = MaterialTheme.colors.onPrimary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                painter = painterResource(
                    id = feedPost.contentImageId
                ),
                contentDescription = null,
                contentScale = ContentScale.FillWidth

            )
            Spacer(modifier = Modifier.height(8.dp))
            Statistics(
                statistics =  feedPost.statistics,
                onLikeClick, onShareClick, onViewClick, onCommentClick)
        }
    }
}


@Composable
private fun PostHeader(feedPost: FeedPost) {
    Card() {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                painter = painterResource(id = feedPost.avatarResId),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = feedPost.communityName,
                    color = MaterialTheme.colors.onPrimary
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = feedPost.publicationDate,
                    color = MaterialTheme.colors.onSecondary
                )
            }
            Icon(
                imageVector = Icons.Rounded.MoreVert, contentDescription = null,
                tint = MaterialTheme.colors.onSecondary
            )
        }
    }
}

@Composable
private fun Statistics(
    statistics: List<StatisticItem>,
    onLikeClick:(StatisticItem) -> Unit,
    onShareClick:(StatisticItem) -> Unit,
    onViewClick:(StatisticItem) -> Unit,
    onCommentClick:(StatisticItem) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        val viewItem = statistics.getItemType(StatisticType.VIEWS)
        Row(modifier = Modifier.weight(1f)) {
            IconWithText(
                icon = R.drawable.baseline_remove_red_eye_24,
                text = viewItem.count.toString(),
                onItemClick = {
                    onViewClick(viewItem)
                }
            )
        }
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val sharedItem = statistics.getItemType(StatisticType.SHARES)
            val commentItem = statistics.getItemType(StatisticType.COMMENTS)
            val likesItem = statistics.getItemType(StatisticType.LIKES)
            IconWithText(icon = R.drawable.baseline_share_24, text = sharedItem.count.toString(),
                onItemClick = {
                    onShareClick(sharedItem)
                })
            IconWithText(icon = R.drawable.baseline_message_24, text = commentItem.count.toString(),
                onItemClick = {
                    onCommentClick(commentItem)
                })
            IconWithText(icon = R.drawable.baseline_heart_broken_24, text = likesItem.count.toString(),
                onItemClick = {
                    onLikeClick(likesItem)
                })
        }
    }
}

private fun List<StatisticItem>.getItemType(type: StatisticType): StatisticItem {
    return this.find { it.type == type } ?: throw  IllegalStateException()
}

@Composable
private fun IconWithText(
    icon: Int,
    text: String,
    onItemClick: () -> Unit
) {
    Row(
        modifier = Modifier.clickable {
            onItemClick()
        },
    ) {
        Icon(
            painter = painterResource(id = icon), contentDescription = null,
            tint = MaterialTheme.colors.onSecondary
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = text, color = MaterialTheme.colors.onSecondary)
    }
}

@Preview
@Composable
private fun PreviewLight() {
//    VKClientTheme(darkTheme = false) {
//        PostCard()
//    }
}

@Preview
@Composable
private fun PreviewDark() {
//    VKClientTheme(darkTheme = true) {
//        PostCard()
//    }
}