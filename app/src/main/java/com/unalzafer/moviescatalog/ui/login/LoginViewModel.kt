package com.unalzafer.moviescatalog.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.unalzafer.moviescatalog.model.login.LoginUserRequest
import com.unalzafer.moviescatalog.model.login.LoginUserResponse
import com.unalzafer.moviescatalog.repository.LoginRepository
import com.unalzafer.moviescatalog.utils.SecurePreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository,private val spm: SecurePreferencesManager) : ViewModel() {

    private val _dataLoginUser = MutableLiveData<LoginUserResponse>()
    val dataLoginUser: LiveData<LoginUserResponse> = _dataLoginUser

    fun setSessionId(token: String) {
        spm.sessionId = token
    }
    fun setAccountId(token: String) {
        spm.accountId = token
    }

    fun login(username: String, password: String)=viewModelScope.launch {
        loginRepository.login(LoginUserRequest(username,password)).let { response->
            if (response.isSuccessful){
                _dataLoginUser.postValue(response.body())
            }else{
                Log.e("Error", "getAllMovies Error: ${response.code()}")
            }
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            //_loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            //_loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            //_loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 1
    }
}