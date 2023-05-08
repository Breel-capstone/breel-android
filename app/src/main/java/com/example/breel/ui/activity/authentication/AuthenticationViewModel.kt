package com.example.breel.ui.activity.authentication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breel.data.Resource
import com.example.breel.data.repository.user.UserRepository
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    val loginResultLiveData: MutableLiveData<Resource<AuthResult>> by lazy {
        MutableLiveData<Resource<AuthResult>>()
    }

    val registerResultLiveData: MutableLiveData<Resource<AuthResult>> by lazy {
        MutableLiveData<Resource<AuthResult>>()
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            userRepository.login(email, password).collect {
                loginResultLiveData.postValue(it)
            }
        }
    }

    fun register(email: String, password: String) {
        viewModelScope.launch {
            userRepository.register(email, password).collect {
                registerResultLiveData.postValue(it)
            }
        }
    }
}