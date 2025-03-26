package com.elewa.si_ware_task.modules.home.data.repository

import com.elewa.si_ware_task.core.expections.NetworkException
import com.elewa.si_ware_task.core.expections.ServerException
import com.elewa.si_ware_task.core.expections.UnknownException
import com.elewa.si_ware_task.modules.home.data.entity.ArticleEntity
import com.elewa.si_ware_task.modules.home.data.mapper.toEntity
import com.elewa.si_ware_task.modules.home.data.source.local.ArticleDao
import com.elewa.si_ware_task.modules.home.data.source.remote.ArticleApiInterface
import com.elewa.si_ware_task.modules.home.domain.dto.ArticleDto
import com.elewa.si_ware_task.modules.home.domain.mapper.toDto
import com.elewa.si_ware_task.modules.home.domain.repository.ArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.zip
import okio.IOException
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val remote: ArticleApiInterface,
    private val local: ArticleDao
): ArticleRepository {

    override fun getArticleList(): Flow<List<ArticleDto>> {
        return flow {
                val response1 = remote.getArticleList1()
                val response2 = remote.getArticleList2()

                if (response1.isSuccessful || response2.isSuccessful) {
                    val articles1 = response1.body()?.articles?.toEntity() ?: emptyList()
                    val articles2 = response2.body()?.articles?.toEntity() ?: emptyList()
                    val mergedArticles = articles1 + articles2

                    local.deleteAllArticle()
                    local.insertAll(mergedArticles)
                }else{
                    when(response1.code()){
                        401 ->{
                             throw NetworkException
                        }
                        501->{
                            throw ServerException
                        }
                        else ->{
                            throw UnknownException
                        }
                    }

                }


            emit(local.getAllArticle().toDto())
        }.catch { e ->
            if(e is IOException){
                val list = local.getAllArticle().toDto()
                if (list.isEmpty()){
                    emit(emptyList())
                    throw e
                }else{
                    emit(list)
                }
            }else{
                emit(emptyList())
                throw e
            }

        }.flowOn(Dispatchers.IO)
    }

    override fun search(query: String): Flow<List<ArticleDto>> {
        return flow {
            val response = remote.searchOnArticle(query)

            if (response.isSuccessful) {
                val articles = response.body()?.articles?.toEntity() ?: emptyList()
                emit(articles.toDto())
            }else{
                when(response.code()){
                    401 ->{
                        throw NetworkException
                    }
                    501->{
                        throw ServerException
                    }
                    else ->{
                        throw UnknownException
                    }
                }
            }

        }.catch { e ->
            emit(emptyList())
            throw e
        }.flowOn(Dispatchers.IO)
    }


}