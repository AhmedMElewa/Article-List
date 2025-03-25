package com.elewa.si_ware_task.modules.detail.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.elewa.si_ware_task.R
import com.elewa.si_ware_task.base.BaseFragment
import com.elewa.si_ware_task.databinding.FragmentDetailsBinding


class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {

    private val args: DetailsFragmentArgs by navArgs()

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailsBinding
        get() = FragmentDetailsBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val article = args.article

        binding.txtTitle.text = article.title
        binding.txtAuthor.text = article.author
        binding.txtDesc.text = article.description
        Glide.with(requireContext()).load(article.previewUrl).apply(
            RequestOptions().placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_photo)
        ).into(binding.imgArticle)
    }

}