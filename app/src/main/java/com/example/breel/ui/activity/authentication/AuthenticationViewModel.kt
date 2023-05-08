package com.example.breel.ui.activity.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breel.data.Resource
import com.example.breel.data.repository.user.UserRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _loginFlow = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Resource<FirebaseUser>?> = _loginFlow

    val currentUser: FirebaseUser?
        get() = userRepository.currentUser

    init {
        if (userRepository.currentUser != null) {
            _loginFlow.value = Resource.Success(userRepository.currentUser!!)
        }
    }

    fun signInWithGoogle(email: String, password: String) = viewModelScope.launch {
        _loginFlow.value = Resource.Loading()
        val result = userRepository.signInWithGoogle(email, password)
        _loginFlow.value = result
    }
}