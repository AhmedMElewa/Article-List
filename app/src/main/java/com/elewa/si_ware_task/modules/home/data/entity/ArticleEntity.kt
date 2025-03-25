package com.elewa.si_ware_task.modules.home.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Article")
data class ArticleEntity(
    @ColumnInfo
    val title: String?,
    @ColumnInfo
    val description: String?,
    @ColumnInfo(name = "path")
    val previewUrl: String?,
    @ColumnInfo
    val author: String?,
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}