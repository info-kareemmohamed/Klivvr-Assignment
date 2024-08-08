package com.example.klivvrtask.di

import com.example.klivvrtask.data.repository.CityRepositoryImpl
import com.example.klivvrtask.domain.model.City
import com.example.klivvrtask.domain.repository.CityRepository
import com.example.klivvrtask.domain.use_case.CitySearchUseCase
import com.example.klivvrtask.domain.use_case.ShowCityLocationUseCase
import com.example.klivvrtask.domain.use_case.SortCitiesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.TreeMap
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {


    @Provides
    @Singleton
    fun provideCityRepository(): CityRepository {
        return CityRepositoryImpl()
    }


    @Provides
    fun provideShowCityLocationUseCase(): ShowCityLocationUseCase {
        return ShowCityLocationUseCase()
    }

    @Provides
    fun provideSortCitiesUseCase(): SortCitiesUseCase {
        return SortCitiesUseCase()
    }

    @Provides
    @Singleton
    fun provideCityAutocomplete(): TreeMap<String, City> {
        return TreeMap<String, City>()
    }

    @Provides
    fun provideCitySearchUseCase( cityMap:TreeMap<String, City>): CitySearchUseCase {
        return CitySearchUseCase(cityMap)
    }

}