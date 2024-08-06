package com.example.klivvrtask.presentation.binding_adapter

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("customVisibility")
fun customVisibility(view: View, visibility: Boolean) {
    view.visibility = if (visibility) View.VISIBLE else View.GONE
}