package com.example.klivvrtask.presentation.home_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.klivvrtask.R

class CitiesRecyclerViewAdapter(
    private val citiesList: List<String>,
    private val listener: LocationClickListener
) :
    RecyclerView.Adapter<CitiesRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(city: String) {}


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.home_recycler_card, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return citiesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(citiesList[position])
    }
}

interface LocationClickListener {
    fun locationClicked(location: String)
}