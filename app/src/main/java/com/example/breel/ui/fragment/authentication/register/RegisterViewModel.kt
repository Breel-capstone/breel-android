package com.example.breel.ui.fragment.authentication.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breel.data.Resource
import com.example.breel.data.repository.user.UserRepository
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    val registerResultLiveData: MutableLiveData<Resource<AuthResult>> by lazy {
        MutableLiveData<Resource<AuthResult>>()
    }

    fun register(email: String, password: String) {
        viewModelScope.launch {
            userRepository.register(email, password).collect {
                registerResultLiveData.postValue(it)
            }
        }
    }
}