package com.example.breel.ui.fragment.searching.mentor

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.breel.data.api.mentor.Mentor
import com.example.breel.data.repository.mentor.MentorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MentorViewModel @Inject constructor(
    private val mentorRepository: MentorRepository
) : ViewModel() {
    val mentorPagingLiveData: MutableLiveData<PagingData<Mentor>> by lazy {
        MutableLiveData<PagingData<Mentor>>()
    }

    fun getMentors() {
        viewModelScope.launch {
            mentorRepository.getMentors().collectLatest {
                mentorPagingLiveData.postValue(it)
            }
        }
    }
}