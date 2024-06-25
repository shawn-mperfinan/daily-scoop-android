package com.dailyscoop.app.viewmodel

import com.dailyscoop.app.ArticleUiState
import com.dailyscoop.app.MainUiState
import com.dailyscoop.app.MainVM
import com.dailyscoop.app.NewsUiState
import com.dailyscoop.app.core.model.UserPreferencesData
import com.dailyscoop.app.data.repositories.INewsRepository
import com.dailyscoop.app.data.repositories.IUserPreferencesRepository
import com.dailyscoop.app.fake.repository.FakeNewsRepository
import com.dailyscoop.app.fake.repository.FakeUserPreferencesRepository
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

    private lateinit var userPreferencesRepository: IUserPreferencesRepository

    private lateinit var viewModel: MainVM

    @BeforeEach
    fun setUp() {
        newsRepository = FakeNewsRepository()
        userPreferencesRepository = FakeUserPreferencesRepository()
        viewModel =
            MainVM(
                newsRepository = newsRepository,
                userPreferencesRepository = userPreferencesRepository,
            )
    }

    @Test
    fun `mainUiState should retrieve (MainUiState_Loading) state value when initialized`() =
        runTest {
            assertThat(viewModel.mainUiState.value).isEqualTo(MainUiState.Loading)
        }

    @Test
    fun `mainUiState should retrieve (MainUiState_Success) state value when getIsAppFirstLaunch() is performed`() =
        runTest {
            val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.mainUiState.collect() }

            val isAppFirstLaunch = userPreferencesRepository.getIsAppFirstLaunch().first()
            val userPreferencesData = UserPreferencesData(isAppFirstLaunch = isAppFirstLaunch)

            assertThat(userPreferencesData.isAppFirstLaunch).isTrue()
            assertThat(viewModel.mainUiState.value).isEqualTo(MainUiState.Success(userPreferencesData))

            collectJob.cancel()
        }

    @Test
    fun `newsUiState should retrieve (NewsUiState_Loading) state value when initialized`() =
        runTest {
            assertThat(viewModel.newsUiState.value).isEqualTo(NewsUiState.Loading)
        }

    @Test
    fun `articleUiState should retrieve (ArticleUiState_Loading) state value when initialized`() =
        runTest {
            assertThat(viewModel.articleUiState.value).isEqualTo(ArticleUiState.Loading)
        }

    @Test
    fun `articleUiState should retrieve (ArticleUiState_Success) state value when getArticleInfo() is performed`() =
        runTest {
            val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.articleUiState.collect() }

            val newsArticle =
                newsRepository.getArticleInfo(
                    newsId = 2,
                    externalId = "57fe599411e31393e29111b6510c8460",
                ).first()

            assertThat(viewModel.articleUiState.value).isEqualTo(ArticleUiState.Success(newsArticle))

            collectJob.cancel()
        }
}
