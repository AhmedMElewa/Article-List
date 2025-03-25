package com.elewa.si_ware_task.modules.home.data.mapper

import com.elewa.si_ware_task.modules.home.data.entity.ArticleEntity
import com.elewa.si_ware_task.modules.home.data.model.ArticleResponseDetail


fun ArticleResponseDetail.toEntity() = ArticleEntity(
    title = title,
    description = description,
    previewUrl = urlToImage,
    author = author
)

fun List<ArticleResponseDetail>.toEntity() = map { it.toEntity() }

