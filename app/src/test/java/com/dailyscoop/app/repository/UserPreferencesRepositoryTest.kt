package com.dailyscoop.app.repository

import app.cash.turbine.test
import com.dailyscoop.app.data.repositories.IUserPreferencesRepository
import com.dailyscoop.app.data.repositories.UserPreferencesRepository
import com.dailyscoop.app.data.source.datastore.IUserPreferencesDatastore
import com.dailyscoop.app.fake.datastore.FakeUserPreferencesDatastore
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UserPreferencesRepositoryTest {
    private val testScope = TestScope(UnconfinedTestDispatcher())

    private lateinit var userPreferencesDatastore: IUserPreferencesDatastore

    private lateinit var userPreferencesRepository: IUserPreferencesRepository

    @BeforeEach
    fun setup() {
        userPreferencesDatastore = FakeUserPreferencesDatastore()

        userPreferencesRepository = UserPreferencesRepository(userPreferencesDatastore)
    }

    @Test
    fun `getIsAppFirstLaunch should retrieve (true) as IS_APP_FIRST_LAUNCH preference key's value when initialized`() =
        testScope.runTest {
            userPreferencesRepository.getIsAppFirstLaunch().test {
                val isAppFirstLaunch = awaitItem()
                Truth.assertThat(isAppFirstLaunch).isTrue()
            }
        }

    @Test
    fun `getIsAppFirstLaunch should retrieve IS_APP_FIRST_LAUNCH new value when setIsAppFirstLaunch() is performed`() =
        testScope.runTest {
            userPreferencesRepository.setIsAppFirstLaunch(false)
            userPreferencesRepository.getIsAppFirstLaunch().test {
                val isAppFirstLaunch = awaitItem()
                Truth.assertThat(isAppFirstLaunch).isFalse()
            }
        }
}
