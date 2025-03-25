package com.elewa.si_ware_task.modules.home.di

import com.elewa.si_ware_task.modules.home.data.repository.ArticleRepositoryImpl
import com.elewa.si_ware_task.modules.home.domain.repository.ArticleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
abstract class ArticleBind {

    @Binds
    abstract fun provideArticleRepos(repository: ArticleRepositoryImpl): ArticleRepository
}