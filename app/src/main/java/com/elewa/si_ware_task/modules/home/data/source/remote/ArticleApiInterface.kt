package com.elewa.si_ware_task.modules.home.data.source.remote

import com.elewa.si_ware_task.modules.home.data.model.ArticleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleApiInterface {

    @GET("/v2/top-headlines?country=us&page=1")
    suspend fun getArticleList1(): Response<ArticleResponse>

    @GET("/v2/top-headlines?country=us&page=2")
    suspend fun getArticleList2() : Response<ArticleResponse>

    @GET("/v2/top-headlines?country=us")
    suspend fun searchOnArticle(
        @Query("q") query: String
    ) : Response<ArticleResponse>
}