package com.elewa.si_ware_task.modules.home.domain.repository

import com.elewa.si_ware_task.modules.home.data.entity.ArticleEntity
import com.elewa.si_ware_task.modules.home.data.model.ArticleResponse
import com.elewa.si_ware_task.modules.home.domain.dto.ArticleDto
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ArticleRepository {

    fun getArticleList() : Flow<List<ArticleDto>>

    fun search(query: String): Flow<List<ArticleDto>>
}