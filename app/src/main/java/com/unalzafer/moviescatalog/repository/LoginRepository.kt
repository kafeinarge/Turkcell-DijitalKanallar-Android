package com.unalzafer.moviescatalog.repository

import com.unalzafer.moviescatalog.api.ApiService
import com.unalzafer.moviescatalog.model.login.LoginUserRequest
import javax.inject.Inject

class LoginRepository
@Inject
constructor(private val apiService: ApiService) {
        suspend fun login(loginUserRequest: LoginUserRequest)=apiService.login(loginUserRequest)
}