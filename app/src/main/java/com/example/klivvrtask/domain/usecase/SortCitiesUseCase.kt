package com.example.klivvrtask.domain.usecase

import com.example.klivvrtask.domain.model.City

/**
 * Sorts a list of cities based on the specified criteria.
 *
 * @param cities The list of cities to be sorted.
 * @return A list of cities sorted by their starting character, with non-letter starting cities at the end.
 */
class SortCitiesUseCase {

    operator fun invoke(cities: List<City>): List<City> {
        val sortedCities = cities.sortedWith { city1, city2 ->
            val isSpecial1 = city1.name.firstOrNull()?.let { !it.isLetter() } == true
            val isSpecial2 = city2.name.firstOrNull()?.let { !it.isLetter() } == true

            when {
                isSpecial1 && !isSpecial2 -> 1
                !isSpecial1 && isSpecial2 -> -1
                else -> city1.name.lowercase().compareTo(city2.name.lowercase())
            }
        }
        return sortedCities
    }
}