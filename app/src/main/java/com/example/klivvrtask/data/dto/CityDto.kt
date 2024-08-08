package com.example.klivvrtask.data.dto

import com.example.klivvrtask.domain.model.City
import kotlinx.serialization.Serializable

@Serializable
data class CityDto(
    val _id: Int,
    val coord: Coord,
    val country: String,
    val name: String
)

fun CityDto.toCity(): City {
    return City(
        id = _id,
        name = name,
        country = country,
        latitude = coord.lat,
        longitude = coord.lon
    )
}