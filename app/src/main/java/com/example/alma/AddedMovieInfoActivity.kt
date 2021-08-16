package com.example.alma

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.alma.databinding.ActivityAddedMovieInfoBinding
import com.example.alma.databinding.ActivityMovieInfoActivityBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AddedMovieInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddedMovieInfoBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddedMovieInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        val film: Film? = intent.getParcelableExtra("EXTRA_FILM")
        val title = film?.title
        val year = film?.year.toString()
        val genre = film?.genre.toString()
        val rating = film?.rating.toString()
        val runtime = film?.runtime.toString()
        val votes = film?.votes.toString()
        val movieInfo = "$title ($year) \n\n $genre \n\n Runtime: $runtime \n\n Rating: $rating ($votes Votes) "

        binding.tvTitle.text = movieInfo
    }
}