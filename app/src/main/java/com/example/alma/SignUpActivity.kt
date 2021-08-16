package com.example.alma

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.alma.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception

//AppCompatActivity to BaseActivty
class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        binding.btnSignUp.setOnClickListener {
            val email = binding.etUsername.text.toString()
            signUpUser(email)
        }

    }

    private fun signUpUser(email: String){
        if(binding.etUsername.text.toString().isEmpty()){
            binding.etUsername.error = "Enter email"
            binding.etUsername.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(binding.etUsername.text.toString()).matches()){
            binding.etUsername.error = "Invalid email"
            binding.etUsername.requestFocus()
            return
        }

        if(binding.etPassword.text.toString().isEmpty()){
            binding.etPassword.error = "Enter password"
            binding.etPassword.requestFocus()
            return
        }
        auth.createUserWithEmailAndPassword(binding.etUsername.text.toString(), binding.etPassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val firebaseUser: FirebaseUser = task.result!!.user!!

                    val currentUser = User(
                        firebaseUser.uid,
                        email
                    )
                    // register user, give user confirmation on successful signup and go to home page
                    FirestoreClass().registerUser(this@SignUpActivity, currentUser)
                    startActivity(Intent(this, LoginActivity::class.java))
                    Toast.makeText(this@SignUpActivity, "Successfully Registered", Toast.LENGTH_LONG).show()

                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Sign up failed, try again",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}