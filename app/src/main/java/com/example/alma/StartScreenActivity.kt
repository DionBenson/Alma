package com.example.alma

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.alma.databinding.ActivityLoginBinding
import com.example.alma.databinding.ActivityStartScreenBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class StartScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartScreenBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()


        binding.btnAddMovie.setOnClickListener {
            Intent(this, LogMovieActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.btnRecommendation.setOnClickListener {
            Intent(this, RecommendationsActivity::class.java).also {
                startActivity(it)
            }
        }

        binding.btnWatchedMovies.setOnClickListener {
            Intent(this, FilmLogActivity::class.java).also{
                startActivity(it)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.tool_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.miLogOut -> signOut()
            R.id.miSettings -> Toast.makeText(this, "Customization and Settings Coming Soon", Toast.LENGTH_SHORT).show()
            R.id.miDeveloper -> Intent(this, DeveloperActivity::class.java).also {
                startActivity(it)
            }
        }
        return true
    }

    private fun signOut() {

        auth.signOut()
        Intent(this, SignOrLogActivity::class.java).also {
            startActivity(it)
        }
    }
}
