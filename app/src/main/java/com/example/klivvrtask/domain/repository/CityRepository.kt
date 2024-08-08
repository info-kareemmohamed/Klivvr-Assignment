package com.example.klivvrtask.domain.repository

import android.content.Context
import com.example.klivvrtask.common.Resource
import com.example.klivvrtask.domain.model.City
import kotlinx.coroutines.flow.Flow

interface  CityRepository {
     fun getCities(context: Context): Flow<Resource<List<City>>>
}