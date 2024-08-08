package com.example.klivvrtask.domain.use_case

import com.example.klivvrtask.domain.model.City
import junit.framework.TestCase.assertEquals
import org.junit.Test

class SortCitiesUseCaseTest{



    @Test
    fun `test cities are sorted correctly`() {
        val cities = listOf(
            City(id = 1, name = "2nd City", country = "CountryA", latitude = 0.0, longitude = 0.0),
            City(id = 2, name = "1st City", country = "CountryB", latitude = 0.0, longitude = 0.0),
            City(id = 5, name = "@Special City", country = "CountryE", latitude = 0.0, longitude = 0.0),
            City(id = 3, name = "Abc City", country = "CountryC", latitude = 0.0, longitude = 0.0),
            City(id = 4, name = "Zebra City", country = "CountryD", latitude = 0.0, longitude = 0.0)
        )

        val expectedSortedCities = listOf(
            City(id = 3, name = "Abc City", country = "CountryC", latitude = 0.0, longitude = 0.0),
            City(id = 4, name = "Zebra City", country = "CountryD", latitude = 0.0, longitude = 0.0),
            City(id = 2, name = "1st City", country = "CountryB", latitude = 0.0, longitude = 0.0),
            City(id = 1, name = "2nd City", country = "CountryA", latitude = 0.0, longitude = 0.0),
            City(id = 5, name = "@Special City", country = "CountryE", latitude = 0.0, longitude = 0.0)
        )

        val sortCitiesUseCase = SortCitiesUseCase()
        val sortedCities = sortCitiesUseCase(cities)

        assertEquals(expectedSortedCities, sortedCities)
    }
}