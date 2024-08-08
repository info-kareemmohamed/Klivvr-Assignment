package com.example.klivvrtask.data

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.klivvrtask.data.dto.CityDto
import com.example.klivvrtask.data.dto.Coord
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test

class JsonParsingTest {


    private val context = ApplicationProvider.getApplicationContext<Context>()

    @Test
    fun Read_Json_from_assets() {
        val validJsonString = readJsonFromAssets(context, "cities.json")
        // Check if the JSON string is not empty or contains error message
        assertTrue(validJsonString.isNotEmpty() && !validJsonString.contains("Error reading JSON file"))
    }

    @Test
    fun Read_Json_from_assets_with_invalid_file() {
        val invalidJsonString = readJsonFromAssets(context, "invalid_file.json")
        // Check if the result contains the error message
        assertTrue(invalidJsonString.contains("Error reading JSON file"))
    }


    @Test
    fun Parse_Json_string_should_correctly_parse_a_valid_JSON_string_into_a_list_of_CityDto_objects() {
        // Valid JSON string for testing
        val jsonString = """
            [
                {"country":"UA","name":"Hurzuf","_id":707860,"coord":{"lon":34.283333,"lat":44.549999}},
                {"country":"RU","name":"Novinki","_id":519188,"coord":{"lon":37.666668,"lat":55.683334}},
                {"country":"NP","name":"Gorkhā","_id":1283378,"coord":{"lon":84.633331,"lat":28}}
            ]
        """
        // Call parseJsonString to parse the JSON string
        val cities = parseJsonString(jsonString)

        // Expected list of CityDto objects
        val expectedCities = listOf(
            CityDto(country = "UA", name = "Hurzuf", _id = 707860, coord = Coord(lon = 34.283333, lat = 44.549999)),
            CityDto(country = "RU", name = "Novinki", _id = 519188, coord = Coord(lon = 37.666668, lat = 55.683334)),
            CityDto(country = "NP", name = "Gorkhā", _id = 1283378, coord = Coord(lon = 84.633331, lat = 28.0))

        )

        // Verify that the parsed list matches the expected list
        assertEquals(expectedCities, cities)
    }

    @Test
    fun Parse_Json_string_with_invalid_Json() {
        // Invalid JSON string for testing
        val invalidJsonString = """
            [
                {"country":"UA","name":"Hurzuf","_id":707860,"coord":{"lon":34.283333,"lat":44.549999}},
                {"country":"RU","name":"Novinki","_id":519188,"coord":{"lon":37.666668,"lat":55.683334}},
                {"country":"NP","name":"Gorkhā","_id":1283378,"coord":{"lon":84.633331,"lat":28}},
                {"country":"IN","name":"State of Haryāna","_id":1270260,"coord":{"lon":76,"lat":29}},
                {"country":"UA","name":"Holubynka","_id":708546,"coord":{"lon":33.900002,"lat":44.599998}},
                {"country":"XYZ","name":"Invalid City"}  // Invalid entry
            ]
        """
        try {
            // Call parseJsonString to parse the invalid JSON string
            val cities = parseJsonString(invalidJsonString)
            // The function should throw an exception, so this line should not be reached
            assertTrue("Expected exception to be thrown", false)
        } catch (e: Exception) {
            // Verify that an exception was thrown
            assertTrue(e is kotlinx.serialization.SerializationException)
        }
    }
}
