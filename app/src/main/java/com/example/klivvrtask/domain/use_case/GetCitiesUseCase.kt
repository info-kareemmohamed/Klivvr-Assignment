package com.example.klivvrtask.domain.use_case

import android.content.Context
import com.example.klivvrtask.common.Resource
import com.example.klivvrtask.domain.model.City
import com.example.klivvrtask.domain.repository.CityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(private val repository: CityRepository){
    operator fun invoke(context: Context): Flow<Resource<List<City>>> =repository.getCities(context)
}