package com.example.alma

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import com.example.alma.databinding.ActivityLogMovieBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.lang.StringBuilder


// mockup class to show possible future search functionality

class DeveloperActivity : AppCompatActivity() {

    lateinit var binding: ActivityLogMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val search = binding.svBar

        // filler movies used as example
        val names = arrayOf("The Dark Knight (2008)", "Pulp Fiction (1994)", "Moonlight (2016)",
            "Taxi Driver (1976)",  "Spider-Man: Into the Spider-Verse (2018)", "Mortal Kombat (1995)",
            "Mortal Kombat (2021)", "Spirited Away (2001)", "Lilo & Stitch (2002)", "Frozen (2013)",
            "Phantom Thread (2017)")

        val adapter: ArrayAdapter<String> = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, names)

        binding.lvListOfMovies.adapter = adapter

        //******************************************************************************************

        // allows for real time search of strings in array
        search.setOnQueryTextListener(object  :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                search.clearFocus()
                if (names.contains(query)){
                    adapter.filter.filter(query)
                }else{
                    Toast.makeText(applicationContext, "Item not found", Toast.LENGTH_LONG).show()
                }
                return false
            }

            //**************************************************************************************

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })
    }
}