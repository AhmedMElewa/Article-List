package com.elewa.si_ware_task.modules.home.domain.interceptor

import com.elewa.si_ware_task.base.BaseUseCase
import com.elewa.si_ware_task.modules.home.domain.dto.ArticleDto
import com.elewa.si_ware_task.modules.home.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArticleListUseCase @Inject constructor (private val repository: ArticleRepository) :
    BaseUseCase<Unit, Flow<List<ArticleDto>>> {

    override suspend fun execute(params: Unit?): Flow<List<ArticleDto>> {
        return repository.getArticleList()
    }
}