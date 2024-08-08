package com.example.klivvrtask.domain.use_case

import android.content.Context
import com.example.klivvrtask.domain.repository.CityRepository
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(private val repository: CityRepository) {
    operator fun invoke(context: Context) = repository.getCities(context)
}