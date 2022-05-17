package com.hostelworld.qacodechallenge

object RegisteredUsers {
    private val users = mutableListOf<User>()

    fun registerUser(firstName: String?, lastName: String?, email: String?, password: String?): UserRegistrationResult {
        firstName?.takeIf { it.isNotBlank() && it.isNotEmpty() } ?: return UserRegistrationResult.InvalidFirstName
        lastName ?: return UserRegistrationResult.InvalidLastName
        email?.takeIf { it.isNotBlank() && it.isNotEmpty() } ?: return UserRegistrationResult.InvalidEmail
        password?.takeIf { it.isNotBlank() && it.isNotEmpty() } ?: return UserRegistrationResult.InvalidPassword

        val existingUser = users.firstOrNull { it.email == email }

        if(existingUser != null) {
            return UserRegistrationResult.UserAlreadyExists
        }

        val newUser = User(firstName, lastName, email, password)

        users.add(newUser)

        return UserRegistrationResult.Success
    }

    fun login(email: String?, password: String?): LoginResult {
        val user = users.firstOrNull { it.email == email } ?: return LoginResult.InvalidEmail

        if(user.password != password) {
            return LoginResult.InvalidPassword
        }

        return LoginResult.Success(user)
    }
}

sealed class UserRegistrationResult {
    object InvalidFirstName: UserRegistrationResult()
    object InvalidLastName: UserRegistrationResult()
    object InvalidEmail: UserRegistrationResult()
    object InvalidPassword: UserRegistrationResult()
    object UserAlreadyExists: UserRegistrationResult()
    object Success: UserRegistrationResult()
}

sealed class LoginResult {
    object InvalidEmail: LoginResult()
    object InvalidPassword: LoginResult()
    data class Success(val user: User): LoginResult()
}