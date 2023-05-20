package com.example.breel.ui.fragment.authentication.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breel.data.Resource
import com.example.breel.data.repository.user.UserRepository
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel(
) {
    val loginResultLiveData: MutableLiveData<Resource<AuthResult>> by lazy {
        MutableLiveData<Resource<AuthResult>>()
    }

    val signInGoogleResultLiveData: MutableLiveData<Resource<AuthResult>> by lazy {
        MutableLiveData<Resource<AuthResult>>()
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            userRepository.login(email, password).collect {
                loginResultLiveData.postValue(it)
            }
        }
    }

    fun signInWithGoogle(credential: AuthCredential) {
        viewModelScope.launch {
            userRepository.signInWithGoogle(credential).collect {
                signInGoogleResultLiveData.postValue(it)
            }
        }
    }
}