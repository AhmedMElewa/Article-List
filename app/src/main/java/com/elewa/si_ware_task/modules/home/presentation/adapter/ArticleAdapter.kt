package com.elewa.si_ware_task.modules.home.presentation.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.elewa.si_ware_task.R
import com.elewa.si_ware_task.databinding.ArticleItemBinding
import com.elewa.si_ware_task.modules.home.domain.dto.ArticleDto
import java.io.File
import javax.inject.Inject

class ArticleAdapter @Inject constructor(
    ) :
    ListAdapter<ArticleDto, ArticleAdapter.MyViewHolder>(MyDiffUtil) {

    var onArticleSelect: ((ArticleDto) -> Unit)? = null


    companion object MyDiffUtil : DiffUtil.ItemCallback<ArticleDto>() {
        override fun areItemsTheSame(oldItem: ArticleDto, newItem: ArticleDto): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ArticleDto, newItem: ArticleDto): Boolean {
            return oldItem.title == newItem.title
        }
    }

    inner class MyViewHolder(private val binding: ArticleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ArticleDto?) {


                binding.txtTitle.text = item?.title
                item?.previewUrl?.let {
                    Glide.with(binding.root.context).load(item.previewUrl).centerCrop().apply(
                        RequestOptions().placeholder(R.drawable.ic_loading)
                            .error(R.drawable.ic_photo)
                    ).into(binding.imgArticle)
                }

            itemView.setOnClickListener {
                item?.let { it1 -> onArticleSelect?.invoke(it1) }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ArticleItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

}