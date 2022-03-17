package com.unalzafer.moviescatalog.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.unalzafer.moviescatalog.R
import com.unalzafer.moviescatalog.base.BaseActivity
import com.unalzafer.moviescatalog.databinding.ActivityLoginBinding
import com.unalzafer.moviescatalog.helper.enum.ResponseEnum
import com.unalzafer.moviescatalog.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_login)
        setContentView(binding.root)

        val username = binding.username
        val password = binding.password
        val login = binding.login
        val loading = binding.loading



        loginViewModel.dataLoginUser.observe(this@LoginActivity, Observer {
            if(it.responseCode== ResponseEnum.SUCCESS.value){
                if(!it.requestToken.isNullOrEmpty()&&!it.sessionId.isNullOrEmpty()) {
                    loginViewModel.setAccountId(it.requestToken)
                    loginViewModel.setSessionId(it.sessionId)
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }else{
                    showDialog(message = getString(R.string.not_key_error_message))
                }
            }else{
                showDialog(message = it.responseDesc)
            }
        })

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            username.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }

            login.setOnClickListener {
                loginViewModel.login(username.text.toString(), password.text.toString())
            }
        }
    }
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}