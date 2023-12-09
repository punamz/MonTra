package com.punam.montra.src.data.local_data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "on_boarding_pref")

class DataStoreDatabase(context: Context) {

    private val _dataStore = context.dataStore

    suspend fun <T> saveValue(preferencesKey: Preferences.Key<T>, value: T) {
        _dataStore.edit { preferences ->
            preferences[preferencesKey] = value
        }
    }

    suspend fun <T> readValue(preferencesKey: Preferences.Key<T>, default: T): Flow<T> =
        _dataStore.data
            .catch {
                if (it is IOException) emit(emptyPreferences()) else throw it
            }
            .map { it[preferencesKey] ?: default }

    suspend fun <T> deleteValue(preferencesKey: Preferences.Key<T>) {
        _dataStore.edit { preferences ->
            if (preferences.contains(preferencesKey)) preferences.remove(preferencesKey)
        }
    }
}

object PreferencesKey {
    val onBoardingKey = booleanPreferencesKey(name = "on_boarding_completed")
    val userId = stringPreferencesKey(name = "user_id")
    val userToken = stringPreferencesKey(name = "user_token")
    val userName = stringPreferencesKey(name = "user_name")
    val userEmail = stringPreferencesKey(name = "user_email")
}