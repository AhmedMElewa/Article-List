package com.elewa.si_ware_task.modules.home.domain.mapper

import com.elewa.si_ware_task.modules.home.data.entity.ArticleEntity
import com.elewa.si_ware_task.modules.home.domain.dto.ArticleDto


fun ArticleEntity.toDto() = ArticleDto(
    title = title,
    description = description,
    previewUrl = previewUrl,
    author = author
)

fun List<ArticleEntity>.toDto() = map { it.toDto() }
