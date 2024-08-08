package com.example.klivvrtask.domain.use_case

import com.example.klivvrtask.common.CityAutocomplete
import com.example.klivvrtask.domain.model.City
import javax.inject.Inject

class CitySearchUseCase @Inject constructor(private val citySearch: CityAutocomplete) {
    operator fun invoke(prefix: String): List<City> {
        return citySearch.findCitiesByPrefix(prefix)
    }

    fun addCities(cities: List<City>) =cities.forEach { citySearch.addCity(it) }

}