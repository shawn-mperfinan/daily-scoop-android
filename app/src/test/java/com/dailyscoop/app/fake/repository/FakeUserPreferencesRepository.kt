package com.dailyscoop.app.fake.repository

import com.dailyscoop.app.data.repositories.IUserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeUserPreferencesRepository : IUserPreferencesRepository {
    @Suppress("EmptyFunctionBlock")
    override suspend fun setIsAppFirstLaunch(value: Boolean) {
    }

    override fun getIsAppFirstLaunch(): Flow<Boolean> {
        return flowOf(true)
    }
}
