package com.example.alma

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class AddedFilmArrayAdapter(private val filmList: ArrayList<Film>) : RecyclerView.Adapter<AddedFilmArrayAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView){

        // information that will be displayed on each item in recyclerview
        val title : TextView = itemView.findViewById(R.id.tvTitle)
        val year : TextView = itemView.findViewById(R.id.tvYear)

        // make film object from attributes of film from selected item in recyclerview
        // then send object with attributes to a screen to display attributes
        init {
            itemView.setOnClickListener{
                val position: Int = absoluteAdapterPosition
                val intent = Intent(itemView.context, AddedMovieInfoActivity::class.java)
                val film : Film = filmList[position]

                // object that will be sent to MovieInfo activity
                intent.putExtra("EXTRA_FILM", film)
                itemView.context.startActivity(intent)
            }
        }
    }

    //**********************************************************************************************

    // next 3 functions create recyclerview populated with films
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddedFilmArrayAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AddedFilmArrayAdapter.MyViewHolder, position: Int) {
        val film : Film = filmList[position]
        holder.title.text = film.title
        holder.year.text = film.year.toString()
    }

    override fun getItemCount(): Int {
        return filmList.size
    }

}