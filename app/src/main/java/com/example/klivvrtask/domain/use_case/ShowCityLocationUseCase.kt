package com.example.klivvrtask.domain.use_case

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.example.klivvrtask.domain.model.City

class ShowCityLocationUseCase {
    operator fun invoke(city: City, context: Context) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("geo:${city.latitude},${city.longitude}?q=${city.latitude},${city.longitude}(${city.name})")
            if (context !is Activity) {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        }
        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            println(e.message)
        }
    }
}