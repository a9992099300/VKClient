package com.a9992099300.vkclient.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.a9992099300.vkclient.MainViewModel


@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun VKViewMainScreen(viewModel: MainViewModel) {
    Scaffold(
        bottomBar = {
            BottomNavigation() {
                val selectedItem = remember {
                    mutableStateOf(0)
                }
                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favorite,
                    NavigationItem.Profile
                )
                items.forEachIndexed { index, it ->
                    BottomNavigationItem(
                        selected = selectedItem.value == index,
                        onClick = { selectedItem.value = index },
                        icon = {
                            Icon(it.icon, contentDescription = null)
                        },
                        label = {
                            Text(text = stringResource(id = it.titleResId))
                        },
                        selectedContentColor = MaterialTheme.colors.onPrimary,
                        unselectedContentColor = MaterialTheme.colors.onSecondary
                    )
                }
            }
        }

    ) {
        val feedPosts = viewModel.feedPosts.observeAsState(listOf())

        LazyColumn(
            modifier = Modifier.padding(it),
            contentPadding = PaddingValues(
                top = 16.dp,
                start = 8.dp,
                end = 8.dp,
                bottom = 72.dp
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = feedPosts.value,
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
                        onCommentClick = { statistic ->
                            viewModel.updateCount(feedPost, statistic)
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
}