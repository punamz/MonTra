package com.punam.montra.util

import com.punam.montra.src.data.local_data.DataStoreDatabase
import com.punam.montra.src.data.local_data.PreferencesKey

class AppHelper {
    suspend fun handleUnauthorized(database: DataStoreDatabase) {
        database.deleteValue(PreferencesKey.userToken)
        database.deleteValue(PreferencesKey.userEmail)
        database.deleteValue(PreferencesKey.userId)
        database.deleteValue(PreferencesKey.userName)
    }
}