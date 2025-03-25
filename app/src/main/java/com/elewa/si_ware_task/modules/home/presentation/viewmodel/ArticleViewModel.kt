package com.elewa.si_ware_task.modules.home.presentation.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elewa.si_ware_task.R
import com.elewa.si_ware_task.core.expections.NetworkException
import com.elewa.si_ware_task.core.expections.ServerException
import com.elewa.si_ware_task.core.expections.UnknownException
import com.elewa.si_ware_task.modules.home.domain.dto.ArticleDto
import com.elewa.si_ware_task.modules.home.domain.interceptor.GetArticleListUseCase
import com.elewa.si_ware_task.modules.home.domain.interceptor.SearchOnArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.http.Query
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val getArticleListUseCase: GetArticleListUseCase,
    private val searchOnArticleUseCase: SearchOnArticleUseCase
) : ViewModel() {


    private val _loading: MutableStateFlow<Boolean> = MutableStateFlow<Boolean>(false)
    val loading: StateFlow<Boolean>
        get() {
            return _loading
        }

    private val _error: MutableSharedFlow<Int> = MutableSharedFlow<Int>()
    val error: SharedFlow<Int>
        get() {
            return _error
        }


    private val _data: MutableStateFlow<List<ArticleDto>?> = MutableStateFlow<List<ArticleDto>?>(
        emptyList()
    )
    val data: StateFlow<List<ArticleDto>?>
        get() {
            return _data
        }


    init {
        getArticleList()
    }
    fun getArticleList(){
        _loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            getArticleListUseCase.execute(Unit)
                .catch { e -> e.handleError() }
                .collect { result -> _data.value = result }
            _loading.value = false
        }
    }


    fun search(query: String){
        _loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            searchOnArticleUseCase.execute(query)
                .catch { e -> e.handleError() }
                .collect { result -> _data.value = result }
            _loading.value = false
        }
    }


    private fun updateError(@StringRes message: Int) {
        viewModelScope.launch {
            _error.emit(message)
        }
    }

    private fun Throwable.handleError() {
        when (this@handleError) {
            is NetworkException -> updateError(R.string.no_internet)
            is ServerException -> updateError(R.string.server_error)
            is UnknownException -> updateError(R.string.unknown_error)
            is IOException -> updateError(R.string.no_internet)
            else -> updateError(R.string.unknown_error)
        }
    }


}