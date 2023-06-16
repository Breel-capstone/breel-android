package com.example.breel.ui.fragment.authentication.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breel.data.Resource
import com.example.breel.data.api.BackendResponseNoData
import com.example.breel.data.api.user.detail.UserExperience
import com.example.breel.data.api.user.detail.UserProjectExperience
import com.example.breel.data.api.user.detail.UserSkill
import com.example.breel.data.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterDetailViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    val registerDetailResultLiveData: MutableLiveData<Resource<BackendResponseNoData>> by lazy {
        MutableLiveData<Resource<BackendResponseNoData>>()
    }


    fun registerDetail(
        fullName: String,
        title: String,
        description: String,
        userExperiences: List<UserExperience>,
        userSkills: List<UserSkill>,
        userProjectExperiences: List<UserProjectExperience>
    ) {
        viewModelScope.launch {
            userRepository.registerDetail(
                fullName,
                title,
                description,
                userExperiences,
                userSkills,
                userProjectExperiences
            ).collect {
                registerDetailResultLiveData.postValue(it)
            }
        }
    }
}