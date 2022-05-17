package com.hostelworld.qacodechallenge

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hostelworld.qacodechallenge.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun intent(context: Context) = Intent(context, LoginActivity::class.java)

        const val RESULT_USER = "user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityLoginBinding.inflate(layoutInflater).run {
            setContentView(root)
            setupUI()
        }
    }

    private fun ActivityLoginBinding.setupUI() {
        btnLogin.setOnClickListener {
            val email = usernameEt.text?.toString()
            val password = passwordEt.text?.toString()

            when(val result = RegisteredUsers.login(email, password)) {
                is LoginResult.InvalidEmail -> {
                    usernameEt.error = "Invalid email"
                }
                is LoginResult.InvalidPassword -> {
                    passwordEt.error = "Invalid password"
                }
                is LoginResult.Success -> {
                    setResult(
                        Activity.RESULT_OK,
                        Intent().apply {
                            putExtra(RESULT_USER, result.user)
                        }
                    )
                    finish()
                }
            }
        }
    }
}