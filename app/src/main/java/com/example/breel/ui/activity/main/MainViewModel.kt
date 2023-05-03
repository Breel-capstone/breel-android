package com.example.breel.ui.activity.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val helloWorld: String
) : ViewModel() {

    fun getHelloWorld(): String {
        return helloWorld
    }
}