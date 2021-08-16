package com.example.alma

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*

class FilmLogActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth
    private lateinit var recyclerView: RecyclerView
    private lateinit var filmArrayList: ArrayList<Film>
    private lateinit var addedFilmArrayAdapter: AddedFilmArrayAdapter
    private lateinit var filmArrayAdapter: FilmArrayAdapter
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_log)

        // auth identifies current user
        auth = FirebaseAuth.getInstance()

        // create recyclerview
        recyclerView = findViewById(R.id.rvMovieList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        // set click listener and ability to pass objects from filmArrayAdapter to recyclerview
        filmArrayList = arrayListOf()
        addedFilmArrayAdapter = AddedFilmArrayAdapter(filmArrayList)
        recyclerView.adapter = addedFilmArrayAdapter

        EventChangeListener()
    }

    // function updates recyclerview with list of movies from current user's profile
    private fun EventChangeListener() {
        db = FirebaseFirestore.getInstance()
        val userID = auth.currentUser?.uid

        // access user's list of movies
        if (userID != null) {
            db.collection("users").document(userID).collection("movies")
                .addSnapshotListener(object : EventListener<QuerySnapshot> {

                    // update recyclerview/film log in real time or log FireStore errors
                    override fun onEvent(
                        value: QuerySnapshot?,
                        error: FirebaseFirestoreException?
                    ) {
                        if (error != null) {
                            Log.e("firestore error", error.message.toString())
                            return
                        }
                        for (doc: DocumentChange in value?.documentChanges!!) {
                            if (doc.type == DocumentChange.Type.ADDED) {
                                filmArrayList.add(doc.document.toObject(Film::class.java))
                            }
                        }
                        addedFilmArrayAdapter.notifyDataSetChanged()
                    }

                })
        }
    }
}