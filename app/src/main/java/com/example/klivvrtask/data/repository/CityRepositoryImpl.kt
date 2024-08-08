package com.example.klivvrtask.data.repository

import android.content.Context
import com.example.klivvrtask.common.Resource
import com.example.klivvrtask.data.dto.CityDto
import com.example.klivvrtask.data.dto.toCity
import com.example.klivvrtask.data.parseJsonFromFile
import com.example.klivvrtask.domain.model.City
import com.example.klivvrtask.domain.repository.CityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CityRepositoryImpl: CityRepository {
    override fun  getCities(context: Context): Flow<Resource<List<City>>> = flow {
        try {
            emit(Resource.Loading())
            val citiesDto: List<CityDto?> = parseJsonFromFile(context, "cities.json")
            val cities = citiesDto.mapNotNull { it?.toCity() }
            emit(Resource.Success(cities.sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER, { it.name }))))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "An error occurred"))
        }
    }


}