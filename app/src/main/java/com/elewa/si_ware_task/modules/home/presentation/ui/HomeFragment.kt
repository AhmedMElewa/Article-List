package com.elewa.si_ware_task.modules.home.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.elewa.si_ware_task.R
import com.elewa.si_ware_task.base.BaseFragment
import com.elewa.si_ware_task.core.extention.addSearchListener
import com.elewa.si_ware_task.core.extention.toGone
import com.elewa.si_ware_task.core.extention.toVisible
import com.elewa.si_ware_task.databinding.FragmentHomeBinding
import com.elewa.si_ware_task.modules.home.presentation.adapter.ArticleAdapter
import com.elewa.si_ware_task.modules.home.presentation.viewmodel.ArticleViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: ArticleViewModel by viewModels()

    @Inject
    lateinit var adapter: ArticleAdapter

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initObservation()
        initErrorObservation()

    }



    private fun initView() {


        binding.layoutError.btnRetry.setOnClickListener {
            if (binding.edtSearch.text.toString().length>=2){
                viewModel.search((binding.edtSearch.text.toString()))
            }else{
                viewModel.getArticleList()
            }

        }

        binding.edtSearch.addSearchListener(
            debounceTime = 500L,
            minLength = 2,
            onSearch = { query ->
                viewModel.search(query)
            },
            onClear = {
                viewModel.getArticleList()
            }
        )

        adapter.onArticleSelect = {
            val action =
                HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it)
            findNavController().navigate(action)
        }
        binding.recArticles.adapter = adapter
    }

    private fun initObservation() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.data.collect { state ->

                    binding.layoutLoading.clLoading.toGone()
                    binding.layoutError.clError.toGone()
                    binding.recArticles.toVisible()
                    adapter.submitList(state)


                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loading.collect { state ->
                    if (state) {
                        binding.layoutError.clError.toGone()
                        binding.layoutLoading.clLoading.toVisible()
                    } else {
                        binding.layoutLoading.clLoading.toGone()
                    }
                }
            }
        }
    }

    private fun initErrorObservation() {
        lifecycleScope.launchWhenStarted {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.error.collectLatest { error ->
                    binding.layoutLoading.clLoading.toGone()
                    binding.recArticles.toGone()
                    binding.layoutError.clError.toVisible()
                    binding.layoutError.tvError.setText(error)

                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}