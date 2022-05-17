package com.hostelworld.qacodechallenge

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import com.hostelworld.qacodechallenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var userLogin: ActivityResultLauncher<Any?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityMainBinding.inflate(layoutInflater).run {
            setContentView(root)
            setupUI(this)
        }

        userLogin = registerForActivityResult(userLoginContract) { user: User? ->
            user?.let {
                val userDetailsIntent = UserDetailsActivity.intent(this, it)
                startActivity(userDetailsIntent)
            }
        }
    }

    private fun setupUI(binding: ActivityMainBinding) = with(binding) {
        btnCreateAccount.setOnClickListener {
            startActivity(Intent(this@MainActivity, CreateAccountActivity::class.java))
        }

        btnLogin.setOnClickListener {
            userLogin.launch(null)
        }
    }

    private val userLoginContract = object: ActivityResultContract<Any, User?>() {
        override fun createIntent(context: Context, input: Any?): Intent {
            return LoginActivity.intent(context)
        }

        override fun parseResult(resultCode: Int, intent: Intent?): User? {
            return intent?.takeIf { resultCode == Activity.RESULT_OK }?.let { loginIntent: Intent ->
                loginIntent.getSerializableExtra(LoginActivity.RESULT_USER) as? User
            }
        }

    }

}