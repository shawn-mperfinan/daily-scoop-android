package com.dailyscoop.app.datastore

import app.cash.turbine.test
import com.dailyscoop.app.data.source.datastore.IUserPreferencesDatastore
import com.dailyscoop.app.fake.datastore.FakeUserPreferencesDatastore
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class UserPreferencesDatastoreTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    private val testScope = TestScope(UnconfinedTestDispatcher())

    private lateinit var userPreferencesDatastore: IUserPreferencesDatastore

    @BeforeEach
    fun setup() {
        userPreferencesDatastore = FakeUserPreferencesDatastore()
    }

    @Test
    fun `getIsAppFirstLaunch should retrieve (true) as IS_APP_FIRST_LAUNCH initial value when performed`() =
        testScope.runTest {
            userPreferencesDatastore.getIsAppFirstLaunch().test {
                val isAppFirstLaunch = awaitItem()
                assertThat(isAppFirstLaunch).isTrue()
            }
        }

    @Test
    fun `getIsAppFirstLaunch should retrieve IS_APP_FIRST_LAUNCH new value when setIsAppFirstLaunch is performed`() =
        testScope.runTest {
            userPreferencesDatastore.setIsAppFirstLaunch(false)
            userPreferencesDatastore.getIsAppFirstLaunch().test {
                val isAppFirstLaunch = awaitItem()
                assertThat(isAppFirstLaunch).isFalse()
            }
        }
}
