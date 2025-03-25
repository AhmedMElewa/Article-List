package com.elewa.si_ware_task.modules.home.di

import com.elewa.si_ware_task.core.data.local.SiWareDB
import com.elewa.si_ware_task.modules.home.data.source.local.ArticleDao
import com.elewa.si_ware_task.modules.home.data.source.remote.ArticleApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ArticleModule {

    @Provides
    @Singleton
    fun provideArticleModule(retrofit: Retrofit): ArticleApiInterface{
        return retrofit.create(ArticleApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideArticleDaoModule(database: SiWareDB): ArticleDao {
        return database.articleDao()
    }

}