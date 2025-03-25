package com.elewa.si_ware_task.modules.home.domain.dto

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class ArticleDto(
    val title: String?,
    val previewUrl: String?,
    val description: String?,
    val author: String?,
): Parcelable