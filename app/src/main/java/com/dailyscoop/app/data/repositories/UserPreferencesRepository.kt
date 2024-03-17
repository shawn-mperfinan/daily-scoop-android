package com.dailyscoop.app.data.repositories

import com.dailyscoop.app.data.source.datastore.IUserPreferencesDatastore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserPreferencesRepository @Inject constructor(
    private val userPreferencesDataSource: IUserPreferencesDatastore,
) : IUserPreferencesRepository {
    override suspend fun setIsAppFirstLaunch(value: Boolean) {
        userPreferencesDataSource.setIsAppFirstLaunch(value)
    }

    override fun getIsAppFirstLaunch(): Flow<Boolean> {
        return userPreferencesDataSource.getIsAppFirstLaunch()
    }
}
