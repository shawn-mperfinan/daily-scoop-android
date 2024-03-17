package com.dailyscoop.app.data.source.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import com.dailyscoop.app.utilities.manager.PreferencesDatastoreManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserPreferencesDatastore @Inject constructor(
    private val datastoreManager: PreferencesDatastoreManager,
) : IUserPreferencesDatastore {
    override suspend fun setIsAppFirstLaunch(value: Boolean) {
        datastoreManager.store(
            preferenceKey = IS_APP_FIRST_LAUNCH,
            value = value,
        )
    }

    override fun getIsAppFirstLaunch(): Flow<Boolean> {
        return datastoreManager.retrieve(
            preferenceKey = IS_APP_FIRST_LAUNCH,
            defaultValue = true,
        )
    }

    companion object {
        private val IS_APP_FIRST_LAUNCH = booleanPreferencesKey("is_app_first_launch")
    }
}
