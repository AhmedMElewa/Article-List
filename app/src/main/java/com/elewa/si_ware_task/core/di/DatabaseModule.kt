package com.elewa.si_ware_task.core.di

import android.content.Context
import androidx.room.Room
import com.elewa.si_ware_task.core.data.local.SiWareDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Volatile
    private var instance: SiWareDB? = null
    private const val DATABASE_NAME = "SiWareDB"

    @Singleton
    @Provides
    fun provideDatabaseInstance(@ApplicationContext context: Context): SiWareDB {
        return instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also { instance = it }
        }
    }

    private fun buildDatabase(context: Context): SiWareDB {
        return Room.databaseBuilder(context, SiWareDB::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration().build()
    }
}