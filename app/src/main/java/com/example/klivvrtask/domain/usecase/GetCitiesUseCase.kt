package com.example.klivvrtask.domain.usecase

import com.example.klivvrtask.common.Resource
import com.example.klivvrtask.domain.Repository.CityRepository
import com.example.klivvrtask.domain.model.City
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(private val repository: CityRepository) {
    operator fun invoke(): Flow<Resource<List<City>>> = repository.getCities()
}