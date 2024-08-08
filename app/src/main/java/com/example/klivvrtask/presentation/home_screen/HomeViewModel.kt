package com.example.klivvrtask.presentation.home_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.klivvrtask.common.Resource
import com.example.klivvrtask.domain.model.City
import com.example.klivvrtask.domain.use_case.CitySearchUseCase
import com.example.klivvrtask.domain.use_case.GetCitiesUseCase
import com.example.klivvrtask.domain.use_case.ShowCityLocationUseCase
import com.example.klivvrtask.domain.use_case.SortCitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

    private val getCitiesUseCase: GetCitiesUseCase,
    private val sortCitiesUseCase: SortCitiesUseCase,
    private val showCityLocationUseCase: ShowCityLocationUseCase,
    private val citySearchUseCase: CitySearchUseCase,
    application: Application
) : AndroidViewModel(application) {


    private val _cityList = MutableStateFlow<List<City>>(emptyList())
    val cityList: StateFlow<List<City>> = _cityList

    private val _messageError = MutableStateFlow<String>("")
    val messageError: StateFlow<String> = _messageError

    private val _flagError = MutableStateFlow<Boolean>(false)
    val flagError: StateFlow<Boolean> = _flagError

    private val _progressBar = MutableStateFlow<Boolean>(false)
    val progressBar: StateFlow<Boolean> = _progressBar


    init {
        getCities()
    }

    fun searchByPrefix(prefix: String) {
        if (prefix.isNotEmpty() && prefix.isNotBlank()) {
            viewModelScope.launch {
                _progressBar.value = true
                val result = withContext(Dispatchers.IO) {
                    citySearchUseCase(prefix)
                }
                _cityList.value = result
                _progressBar.value = false
            }
        }
    }

    fun showCityLocation(city: City) {
        showCityLocationUseCase(city, getApplication())
    }


    private fun getCities() {
        viewModelScope.launch {
            getCitiesUseCase(getApplication()).collect { result ->
                when (result) {
                    is Resource.Loading -> _progressBar.value = true
                    is Resource.Success -> {
                        _progressBar.value = false
                        citySearchUseCase.addCities(result.data ?: emptyList())
                        _cityList.value = sortCitiesUseCase(result.data ?: emptyList())

                    }

                    is Resource.Error -> {
                        _progressBar.value = false
                        _flagError.value = true
                        _messageError.value = result.message ?: "An unexpected error occurred"
                    }
                }

            }

        }
    }
}