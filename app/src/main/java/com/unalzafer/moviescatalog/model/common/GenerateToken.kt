package com.unalzafer.moviescatalog.model.common

import com.unalzafer.moviescatalog.utils.SecurePreferencesManager
import javax.inject.Inject

class GenerateToken @Inject constructor(private val spm: SecurePreferencesManager) {
    fun requestToken():GenerateTokenRequest{
        return GenerateTokenRequest(requestToken = spm.accountId, sessionId =spm.sessionId )
    }
}