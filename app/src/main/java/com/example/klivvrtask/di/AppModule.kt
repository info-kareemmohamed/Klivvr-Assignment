package com.example.klivvrtask.di

import com.example.klivvrtask.common.CityAutocomplete
import com.example.klivvrtask.data.repository.CityRepositoryImpl
import com.example.klivvrtask.domain.repository.CityRepository
import com.example.klivvrtask.domain.use_case.CitySearchUseCase
import com.example.klivvrtask.domain.use_case.ShowCityLocationUseCase
import com.example.klivvrtask.domain.use_case.SortCitiesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
    fun provideCityAutocomplete(): CityAutocomplete {
        return CityAutocomplete()
    }

    @Provides
    fun provideCitySearchUseCase(citySearch: CityAutocomplete): CitySearchUseCase {
        return CitySearchUseCase(citySearch)
    }

}