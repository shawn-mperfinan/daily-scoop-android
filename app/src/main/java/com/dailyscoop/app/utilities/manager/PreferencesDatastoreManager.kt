package com.dailyscoop.app.utilities.manager

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * This manager class provides methods for managing user preferences using Preferences DataStore.
 * It offers functionalities to store, clear, and retrieve data associated with specific preference keys.
 */
class PreferencesDatastoreManager @Inject constructor(
    private val datastore: DataStore<Preferences>,
) {
    /**
     * Retrieves value from preferences datastore
     * @param preferenceKey - key value assigned to be retrieved
     */
    fun <T> retrieve(
        preferenceKey: Preferences.Key<T>,
        defaultValue: T,
    ): Flow<T> {
        return datastore.data.map {
            it[preferenceKey] ?: defaultValue
        }
    }

    /**
     * stores or updates the value from preferences datastore
     * @param preferenceKey - key value assigned to be updated
     */
    suspend fun <T> store(
        preferenceKey: Preferences.Key<T>,
        value: T,
    ) {
        datastore.edit {
            it[preferenceKey] = value
        }
    }

    /**
     * Clears up the value from preferences datastore
     * @param preferenceKey - key value assigned to be cleared
     */
    suspend fun <T> clear(preferenceKey: Preferences.Key<T>) {
        datastore.edit {
            if (it.contains(preferenceKey)) it.remove(preferenceKey)
        }
    }
}
