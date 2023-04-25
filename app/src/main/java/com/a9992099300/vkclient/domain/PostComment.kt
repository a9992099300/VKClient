package com.a9992099300.vkclient.domain

import com.a9992099300.vkclient.R

data class PostComment(
    val id: Int,
    val authorName: String = "Author",
    val authorAvatarId: Int = R.drawable.ic_launcher_foreground,
    val commentTest: String = "Text Text Text",
    val publicationDate: String = "14:00"

)
