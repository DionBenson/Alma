package com.example.alma

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*
import com.google.firebase.ktx.Firebase

class LogMovieActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var filmArrayList: ArrayList<Film>
    private lateinit var filmArrayAdapter: FilmArrayAdapter
    private lateinit var db: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_developer)



        recyclerView = findViewById(R.id.rvListOfMovies)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        filmArrayList = arrayListOf()

        filmArrayAdapter = FilmArrayAdapter(filmArrayList)

        recyclerView.adapter = filmArrayAdapter

        EventChangeListener()
    }

    private fun EventChangeListener(){

        db = FirebaseFirestore.getInstance()
        db.collection("movieDatabase").addSnapshotListener(object : EventListener<QuerySnapshot>{
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {

                if(error != null) {
                    Log.e("firestore error", error.message.toString())
                    return

                }
                for(doc: DocumentChange in value?.documentChanges!!){
                    if (doc.type == DocumentChange.Type.ADDED){
                        filmArrayList.add(doc.document.toObject(Film::class.java))
                    }
                }

                filmArrayAdapter.notifyDataSetChanged()
            }

        })

    }


}