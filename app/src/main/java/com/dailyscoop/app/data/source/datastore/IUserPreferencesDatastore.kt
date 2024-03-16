package com.dailyscoop.app.data.source.datastore

import kotlinx.coroutines.flow.Flow

/**
 * This interface defines methods for accessing and modifying user preferences using Preferences DataStore.
 */
interface IUserPreferencesDatastore {
    /**
     * Stores a boolean value indicating whether this is the first app launch.
     * @param value True if it's the first launch, false otherwise.
     */
    suspend fun setIsAppFirstLaunch(value: Boolean)

    /**
     * Retrieves a flow of boolean values indicating whether this is the first app launch.
     * @return Flow of boolean values. By default, it returns true if the key doesn't exist.
     */
    fun getIsAppFirstLaunch(): Flow<Boolean>
}
