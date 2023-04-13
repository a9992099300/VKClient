package com.a9992099300.vkclient.domain

import com.a9992099300.vkclient.R

data class FeedPost(
    val id: Int = 0,
    val communityName: String = "/dev/null",
    val publicationDate: String = "14:00",
    val avatarResId: Int = R.drawable.ic_launcher_background,
    val contentText: String = "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consecrate, adipisci velit...",
    val contentImageId: Int = R.drawable.ic_launcher_background,
    val statistics: List<StatisticItem> = listOf(
        StatisticItem(type = StatisticType.VIEWS, 966),
        StatisticItem(type = StatisticType.SHARES, 7),
        StatisticItem(type = StatisticType.COMMENTS, 8),
        StatisticItem(type = StatisticType.LIKES, 27),
    )

)