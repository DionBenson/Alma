package com.example.alma

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.alma.databinding.ActivityLogMovieBinding
import com.example.alma.databinding.ActivityMovieInfoActivityBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class MovieInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieInfoActivityBinding

    private lateinit var auth: FirebaseAuth

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieInfoActivityBinding.inflate(layoutInflater)
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

        binding.btnAddMovie.setOnClickListener {
            if (film != null) {
                addToWatched(film)
                Toast.makeText(this, "Movie Added To Your Collection", Toast.LENGTH_SHORT).show()
            }
        }

    }

    // save current film object to
    fun addToWatched(film: Film){
        db = FirebaseFirestore.getInstance()
        auth.currentUser?.let { db.collection("users").document(it.uid)
            .collection("movies").add(film) }
    }
}