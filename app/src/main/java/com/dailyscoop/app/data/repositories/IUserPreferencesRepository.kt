package com.dailyscoop.app.data.repositories

import kotlinx.coroutines.flow.Flow

/**
 * This interface defines methods for accessing and modifying user preferences through a repository layer.
 * The repository layer can potentially handle additional logic or data transformations related to user preferences.
 */
interface IUserPreferencesRepository {
    /**
     * Stores a boolean value indicating whether this is the first app use/launch.
     * @param value True if it's the first launch, false otherwise.
     */
    suspend fun setIsAppFirstLaunch(value: Boolean)

    /**
     * Retrieves a flow of boolean values indicating whether this is the first app launch.
     * @return Flow of boolean values. The behavior related to default values might depend on the implementation.
     */
    fun getIsAppFirstLaunch(): Flow<Boolean>
}
