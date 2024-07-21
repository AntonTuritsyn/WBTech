package com.turitsynanton.android.wbtech.ui.screens.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turitsynanton.android.wbtech.domain.models.DomainMeeting
import com.turitsynanton.android.wbtech.domain.repository.MeetingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MeetingsViewModel(private val repository: MeetingRepository) : ViewModel() {

    //      исправить импорты
    private val _meetingsList: MutableStateFlow<List<DomainMeeting>> = MutableStateFlow(emptyList())
    private val meetingsList: StateFlow<List<DomainMeeting>> = _meetingsList.asStateFlow()
    fun getMeetingsListFlow(): StateFlow<List<DomainMeeting>> = meetingsList

    init {
        getMeetingsList()
    }

    fun getMeetingsList() {
        viewModelScope.launch {
            repository.getMeetingsList().collect { meetingList ->
                _meetingsList.update { meetingList }
            }
        }
    }
}