package com.example.alma

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.alma.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            doLogin()
        }

    }

    private fun doLogin() {
        if (binding.etUsername.text.toString().isEmpty()) {
            binding.etUsername.error = "Enter email"
            binding.etUsername.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(binding.etUsername.text.toString()).matches()) {
            binding.etUsername.error = "Incorrect email"
            binding.etUsername.requestFocus()
            return
        }

        if (binding.etPassword.text.toString().isEmpty()) {
            binding.etPassword.error = "Enter password"
            binding.etPassword.requestFocus()
            return
        }

        auth.signInWithEmailAndPassword(
            binding.etUsername.text.toString(),
            binding.etPassword.text.toString()
        )
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        baseContext, "Welcome " + binding.etUsername.text.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    reload(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        baseContext, "Login failed, try again",
                        Toast.LENGTH_SHORT
                    ).show()
                    reload(null)
                }
            }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload(currentUser);
        }
    }

    private fun reload(currentUser: FirebaseUser?) {

        if (currentUser != null) {
            Intent(this, StartScreenActivity::class.java).also {
                startActivity(it)
                finish()
            }

        }
    }
}
