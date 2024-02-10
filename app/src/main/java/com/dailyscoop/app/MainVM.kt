@file:Suppress("MagicNumber")

package com.dailyscoop.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dailyscoop.app.data.repositories.INewsRepository
import com.dailyscoop.app.model.Article
import com.dailyscoop.app.model.Headline
import com.dailyscoop.app.utilities.Result
import com.dailyscoop.app.utilities.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainVM
    @Inject
    constructor(
        newsRepository: INewsRepository,
    ) : ViewModel() {
        val newsUiState: StateFlow<NewsUiState> =
            newsUiState(
                newsRepository = newsRepository,
            ).stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = NewsUiState.Loading,
            )

        val articleUiState: StateFlow<ArticleUiState> =
            articleUiState(newsRepository).stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = ArticleUiState.Loading,
            )
    }

private fun articleUiState(newsRepository: INewsRepository): Flow<ArticleUiState> {
    /**
     * TODOs:
     * - getting of article info should be dynamic
     * - update unit testing for the above todo scenario
     */
    return newsRepository.getArticleInfo(
        newsId = 2,
        externalId = "57fe599411e31393e29111b6510c8460",
    )
        .asResult()
        .map { result ->
            when (result) {
                is Result.Error -> ArticleUiState.Error
                is Result.Loading -> ArticleUiState.Loading
                is Result.Success -> ArticleUiState.Success(result.data)
            }
        }
}

private fun newsUiState(newsRepository: INewsRepository): Flow<NewsUiState> {
    /**
     * TODOs:
     * - getting of latest headlines implementation needs to be updated on its data layer part
     * - add unit testing for the above todo scenario
     */
    return newsRepository.getLatestHeadlines().map { result ->
        when (result) {
            is Result.Loading -> NewsUiState.Loading
            is Result.Success -> NewsUiState.Success(result.data)
            is Result.Error -> NewsUiState.Error
        }
    }
}

sealed interface NewsUiState {
    data class Success(val headlines: List<Headline>) : NewsUiState

    object Error : NewsUiState

    object Loading : NewsUiState
}

sealed interface ArticleUiState {
    data class Success(val article: Article) : ArticleUiState

    object Error : ArticleUiState

    object Loading : ArticleUiState
}
