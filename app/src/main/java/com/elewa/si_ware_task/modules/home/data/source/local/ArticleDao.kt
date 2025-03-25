package com.elewa.si_ware_task.modules.home.data.source.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.elewa.si_ware_task.modules.home.data.entity.ArticleEntity

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articles: List<ArticleEntity>): List<Long>

    @Query("SELECT * FROM article")
    fun getAllArticle(): List<ArticleEntity>

    @Query("DELETE FROM article")
    suspend fun deleteAllArticle()

}