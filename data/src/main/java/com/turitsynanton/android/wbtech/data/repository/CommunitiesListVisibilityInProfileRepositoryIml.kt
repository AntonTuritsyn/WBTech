package com.turitsynanton.android.wbtech.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.turitsynanton.android.wbtech.domain.repository.InfoVisibilityInProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CommunitiesListVisibilityInProfileRepositoryIml(
    private val dataStore: DataStore<Preferences>
) : InfoVisibilityInProfileRepository {
    override suspend fun setVisibilityInProfile(key: String, isVisible: Boolean) {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(key)] = isVisible
        }
    }

    override fun getVisibilityInProfile(key: String): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[booleanPreferencesKey(key)] ?: true
        }
    }

    companion object {
        private val COMMUNITIES_VISIBILITY_KEY = stringPreferencesKey("COMMUNITIES_VISIBILITY_KEY")
    }
}