package com.hostelworld.qacodechallenge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hostelworld.qacodechallenge.databinding.ActivityCreateAccountBinding

class CreateAccountActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityCreateAccountBinding.inflate(layoutInflater).run {
            setContentView(root)
            setupUI()
        }
    }

    private fun ActivityCreateAccountBinding.setupUI() {
        btnCreateAccount.setOnClickListener {
            val firstName = firstNameEt.text?.toString()
            val lastName = lastNameEt.text?.toString()
            val email = emailEt.text?.toString()
            val password = passwordEt.text?.toString()

            when(RegisteredUsers.registerUser(firstName, lastName, email, password)) {
                is UserRegistrationResult.InvalidFirstName -> {
                    firstNameEt.error = "Invalid First name"
                }
                is UserRegistrationResult.InvalidLastName -> {
                    lastNameEt.error = "Invalid Last name"
                }
                is UserRegistrationResult.InvalidEmail -> {
                    emailEt.error = "Invalid email"
                }
                is UserRegistrationResult.InvalidPassword -> {
                    passwordEt.error = "Invalid password"
                }
                is UserRegistrationResult.UserAlreadyExists -> {
                    emailEt.error = "User already exists"
                }
                is UserRegistrationResult.Success -> {
                    finish()
                }
            }
        }
    }
}