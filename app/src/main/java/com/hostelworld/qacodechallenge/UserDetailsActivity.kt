package com.hostelworld.qacodechallenge

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hostelworld.qacodechallenge.databinding.ActivityUserDetailsBinding

private const val EXTRA_USER = "user"

class UserDetailsActivity: AppCompatActivity() {

    companion object {
        @JvmStatic
        fun intent(context: Context, user: User) = Intent(context, UserDetailsActivity::class.java).apply {
            putExtra(EXTRA_USER, user)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityUserDetailsBinding.inflate(layoutInflater).run {
            setContentView(root)
            setupUI()
        }
    }

    private fun ActivityUserDetailsBinding.setupUI() {
        val user = intent.getSerializableExtra(EXTRA_USER) as User
        firstNameTv.text = "First name: %s".format(user.firstName)
        lastNameTv.text = "Last name: %s".format(user.lastName)
        emailTv.text = "Email: %s".format(user.email)
        logoutBtn.setOnClickListener {
            finish()
        }
    }
}