package com.example.breel.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breel.data.Resource
import com.example.breel.data.api.login.LoginResponse
import com.example.breel.data.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    val resultLiveData: MutableLiveData<Resource<LoginResponse>> by lazy {
        MutableLiveData<Resource<LoginResponse>>()
    }

    fun login() {
        viewModelScope.launch {
            userRepository.login().collect {
                resultLiveData.postValue(it)
            }
        }
    }

    fun getDummyString(): String {
        return userRepository.getDummyString()
    }

    fun saveToken(token: String) {
        viewModelScope.launch {
            userRepository.saveToken(token)
        }
    }

    suspend fun getToken(): String {
        var token = ""
        viewModelScope.launch {
            token = userRepository.getToken().first()
        }.join()
        return token
    }
}