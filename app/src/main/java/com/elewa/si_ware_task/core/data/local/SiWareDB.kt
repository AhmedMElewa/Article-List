package com.elewa.si_ware_task.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.elewa.si_ware_task.modules.home.data.entity.ArticleEntity
import com.elewa.si_ware_task.modules.home.data.source.local.ArticleDao

@Database(entities = [ArticleEntity::class], version = 1, exportSchema = false)
abstract class SiWareDB: RoomDatabase() {

    abstract fun articleDao(): ArticleDao

}