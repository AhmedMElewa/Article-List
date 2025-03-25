package com.elewa.si_ware_task.modules.home.data.model

data class ArticleResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleResponseDetail>
)

data class ArticleResponseDetail(
    val title: String,
    val urlToImage: String,
    val description: String,
    val author: String,
)