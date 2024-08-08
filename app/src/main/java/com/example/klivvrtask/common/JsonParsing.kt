package com.example.klivvrtask.common

import android.content.Context
import com.example.klivvrtask.data.dto.CityDto
import kotlinx.serialization.json.Json
import java.io.IOException
import java.io.InputStreamReader

// Function to parse JSON string into a list of CityDto
fun parseJsonString(jsonString: String): List<CityDto> {
    val json = Json { ignoreUnknownKeys = true }
    return json.decodeFromString<List<CityDto>>(jsonString)
}

// Function to read JSON file from assets and return it as a string
fun readJsonFromAssets(context: Context, fileName: String): String {
    return try {
        context.assets.open(fileName).use { inputStream ->
            InputStreamReader(inputStream).use { reader ->
                reader.readText()
            }
        }
    } catch (e: IOException) {
        e.printStackTrace()
        "Error reading JSON file: ${e.message}"
    }
}

// Function to parse JSON file from assets using the two helper functions
fun parseJsonFromFile(context: Context, fileName: String): List<CityDto> {
    val jsonString = readJsonFromAssets(context, fileName)
    return parseJsonString(jsonString)
}
