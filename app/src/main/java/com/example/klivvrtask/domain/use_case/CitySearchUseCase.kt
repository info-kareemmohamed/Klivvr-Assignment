package com.example.klivvrtask.domain.use_case

import com.example.klivvrtask.domain.model.City
import java.util.TreeMap
import javax.inject.Inject

class CitySearchUseCase @Inject constructor(private val cityMap: TreeMap<String, City>) {



    operator fun invoke(prefix: String,): List<City> {
        val lowerCasePrefix = prefix.lowercase()
        val startKey = lowerCasePrefix
        val endKey = lowerCasePrefix + Char.MAX_VALUE

        val result = cityMap.subMap(startKey, true, endKey, false).values.toList()
        return result
    }

    fun addCities(cities: List<City>) =cities.forEach { cityMap[it.name.lowercase()] = it }

}