package com.unalzafer.moviescatalog.utils

import android.content.SharedPreferences
import com.unalzafer.moviescatalog.helper.Constants
import javax.inject.Inject

class SecurePreferencesManager @Inject constructor(private val preferences: SharedPreferences) {

    companion object {
        const val key_session_id = "session_id"
        const val key_account_id = "account_id"
    }

    private fun insert(key: String, value: String) {
        put(key, value)
    }

    private fun getPrefString(key: String) = preferences.getString(key, Constants.EMPTY)

    private fun put(key: String, value: String?) {
        if (value == null) {
            preferences.edit().remove(key).apply()
        } else {
            with(preferences.edit()) {
                putString(key, value)
                apply()
            }
        }
    }

    var sessionId: String
        get() = getPrefString(key_session_id)
            ?: Constants.key_account_id
        set(value) {
            put(key_session_id, value)
        }

    var accountId: String
        get() = getPrefString(key_account_id)
            ?: Constants.EMPTY
        set(value) {
            put(key_account_id, value)
        }
}