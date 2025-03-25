package com.elewa.si_ware_task.modules.home.domain.interceptor

import com.elewa.si_ware_task.base.BaseUseCase
import com.elewa.si_ware_task.modules.home.domain.dto.ArticleDto
import com.elewa.si_ware_task.modules.home.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchOnArticleUseCase @Inject constructor (private val repository: ArticleRepository) :
    BaseUseCase<String, Flow<List<ArticleDto>>> {

    override suspend fun execute(params: String?): Flow<List<ArticleDto>> {
        requireNotNull(params)
        return repository.search(params)
    }
}