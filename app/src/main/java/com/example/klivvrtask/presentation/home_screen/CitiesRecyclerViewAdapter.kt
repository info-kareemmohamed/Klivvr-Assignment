package com.example.klivvrtask.presentation.home_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.klivvrtask.R
import com.example.klivvrtask.domain.model.City

class CitiesRecyclerViewAdapter(
    private var citiesList: List<City>,
    private val listener: LocationClickListener
) :
    RecyclerView.Adapter<CitiesRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(city:City) {
            itemView.findViewById<TextView>(R.id.card_title).text = "${city.name} -${city.country}"
            val subtitle = itemView.findViewById<TextView>(R.id.card_subtitle)
<<<<<<< Updated upstream
            subtitle.text = "Lat: ${city.lat} , Lon: ${city.lon}"
=======
            subtitle.text = "Lat: ${city.latitude} , Lon: ${city.longitude}"
>>>>>>> Stashed changes
            itemView.findViewById<ImageView>(R.id.card_image).setOnClickListener {
                listener.locationClicked(city)
            }
            subtitle.setOnClickListener {
                listener.locationClicked(city)
            }


        }


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

    fun updateData(newList: List<City>) {
        val diffCallback = CityDiffCallback(citiesList, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        citiesList = newList
        diffResult.dispatchUpdatesTo(this)
    }
}


class CityDiffCallback(
    private val oldList: List<City>,
    private val newList: List<City>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldCity = oldList[oldItemPosition]
        val newCity = newList[newItemPosition]
        return oldCity.id == newCity.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldCity = oldList[oldItemPosition]
        val newCity = newList[newItemPosition]
        return oldCity == newCity
    }
}




interface LocationClickListener {
    fun locationClicked(city: City)
}