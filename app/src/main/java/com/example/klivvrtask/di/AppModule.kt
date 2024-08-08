package com.example.klivvrtask.di

<<<<<<< Updated upstream
import com.example.klivvrtask.domain.usecase.ShowCityLocationUseCase
import com.example.klivvrtask.domain.usecase.SortCitiesUseCase
=======
import com.example.klivvrtask.data.repository.CityRepositoryImpl
import com.example.klivvrtask.domain.repository.CityRepository
import com.example.klivvrtask.domain.use_case.ShowCityLocationUseCase
import com.example.klivvrtask.domain.use_case.SortCitiesUseCase
>>>>>>> Stashed changes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
<<<<<<< Updated upstream

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
=======
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {


    @Provides
    @Singleton
    fun provideCityRepository(): CityRepository {
        return CityRepositoryImpl()
    }
>>>>>>> Stashed changes


    @Provides
    fun provideShowCityLocationUseCase(): ShowCityLocationUseCase {
        return ShowCityLocationUseCase()
    }

    @Provides
    fun provideSortCitiesUseCase(): SortCitiesUseCase {
        return SortCitiesUseCase()
    }


}