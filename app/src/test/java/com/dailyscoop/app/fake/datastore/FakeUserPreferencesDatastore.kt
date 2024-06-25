package com.dailyscoop.app.fake.datastore

import com.dailyscoop.app.core.model.UserPreferencesData
import com.dailyscoop.app.data.source.datastore.IUserPreferencesDatastore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update

class FakeUserPreferencesDatastore : IUserPreferencesDatastore {
    private val userPreferencesData =
        MutableStateFlow(
            UserPreferencesData(
                isAppFirstLaunch = true,
            ),
        )

    override suspend fun setIsAppFirstLaunch(value: Boolean) {
        userPreferencesData.update {
            it.copy(isAppFirstLaunch = value)
        }
    }

    @ExperimentalCoroutinesApi
    override fun getIsAppFirstLaunch(): Flow<Boolean> {
        return userPreferencesData.mapLatest {
            it.isAppFirstLaunch
        }
    }
}
