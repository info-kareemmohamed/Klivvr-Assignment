package com.example.klivvrtask.di

import com.example.klivvrtask.domain.usecase.ShowCityLocationUseCase
import com.example.klivvrtask.domain.usecase.SortCitiesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class AppModule {


    @Provides
    fun provideShowCityLocationUseCase(): ShowCityLocationUseCase {
        return ShowCityLocationUseCase()
    }

    @Provides
    fun provideSortCitiesUseCase(): SortCitiesUseCase {
        return SortCitiesUseCase()
    }


}