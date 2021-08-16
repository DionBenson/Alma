package com.example.alma

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.alma.databinding.ActivityRecommendationsBinding
import com.example.alma.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception

class RecommendationsActivity : AppCompatActivity() {


    private var movieDatabase = Firebase.firestore.collection(("movieDatabase"))


    private lateinit var binding: ActivityRecommendationsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecommendationsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnRefresh.setOnClickListener {
            readFireStoreData()
        }


    }

    private fun subscribeToRealTimeUpdates(){
        movieDatabase.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            firebaseFirestoreException.let {
                //Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                //return@addSnapshotListener
            }
            querySnapshot?.let {
                val sb = StringBuilder()
                // loop through all documents
                for (document in it) {
                    val movie = document.toObject<Film>()
                    val title = movie.title.toString()
                    sb.append("$movie\n")
                }
            }
        }
    }

    private fun readFireStoreData() = CoroutineScope(Dispatchers.IO).launch {
        try {
            val querySnapshot = movieDatabase.get().await()
            val sb = StringBuilder()
            // loop through all documents
            for (document in querySnapshot.documents) {
                val movie = document?.toObject<Film>()
                val title = movie?.title


                sb.append("$title\n")
            }
            withContext(Dispatchers.Main) {
                binding.tvComingSoon.text = sb.toString()
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(this@RecommendationsActivity, e.message, Toast.LENGTH_LONG).show()
            }
        }

    }


}