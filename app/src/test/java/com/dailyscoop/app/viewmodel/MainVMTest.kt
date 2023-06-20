package com.dailyscoop.app.viewmodel

import com.dailyscoop.app.ArticleUiState
import com.dailyscoop.app.MainVM
import com.dailyscoop.app.NewsUiState
import com.dailyscoop.app.data.repositories.INewsRepository
import com.dailyscoop.app.fake.repository.FakeNewsRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(MainDispatcherRule::class)
class MainVMTest {

    private lateinit var newsRepository: INewsRepository

    private lateinit var viewModel: MainVM

    @BeforeEach
    fun setUp() {
        newsRepository = FakeNewsRepository()
        viewModel = MainVM(newsRepository = newsRepository)
    }

    @Test
    fun newsUiState_whenInitialized_thenShowLoading() = runTest {
        assertThat(viewModel.newsUiState.value).isEqualTo(NewsUiState.Loading)
    }

    @Test
    fun articleUiState_whenInitialized_thenShowLoading() = runTest {
        assertThat(viewModel.articleUiState.value).isEqualTo(ArticleUiState.Loading)
    }

    @Test
    fun articleUiState_whenSuccess_matchesHeadlinesFromNewsRepository() = runTest {
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.articleUiState.collect() }

        val articleInfoFromRepository = newsRepository.getArticleInfo(
            newsId = 2,
            externalId = "57fe599411e31393e29111b6510c8460"
        ).first()

        assertThat(viewModel.articleUiState.value).isEqualTo(ArticleUiState.Success(articleInfoFromRepository))

        collectJob.cancel()
    }
}
