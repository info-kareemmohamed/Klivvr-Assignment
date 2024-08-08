package com.example.klivvrtask.domain.use_case

import com.example.klivvrtask.domain.model.City
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import java.util.TreeMap

class CitySearchUseCaseTest{

    private lateinit var citySearchUseCase: CitySearchUseCase
    private val cityMap = TreeMap<String, City>()

    @Before
    fun setUp() {
        // Initialize the CitySearchUseCase with an empty TreeMap
        citySearchUseCase = CitySearchUseCase(cityMap)
    }

    @Test
    fun `test city search by prefix`() {
        // Arrange: Add sample cities to the cityMap
        citySearchUseCase.addCities(
            listOf(
                City(id = 1, name = "New York", country = "USA", latitude = 40.7128, longitude = -74.0060),
                City(id = 2, name = "Newark", country = "USA", latitude = 40.7357, longitude = -74.1724),
                City(id = 3, name = "Los Angeles", country = "USA", latitude = 34.0522, longitude = -118.2437)
            )
        )

        // Act: Search for cities with the prefix "new"
        val result = citySearchUseCase.invoke("new")

        // Assert: Verify that the result contains only cities with names starting with "new"
        val expected = listOf(
            City(id = 1, name = "New York", country = "USA", latitude = 40.7128, longitude = -74.0060),
            City(id = 2, name = "Newark", country = "USA", latitude = 40.7357, longitude = -74.1724)
        )

        assertEquals(expected, result)
    }
}