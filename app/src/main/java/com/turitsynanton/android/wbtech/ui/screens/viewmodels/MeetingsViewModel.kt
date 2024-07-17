package com.turitsynanton.android.wbtech.ui.screens.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turitsynanton.android.wbtech.data.storage.models.Meeting
import com.turitsynanton.android.wbtech.domain.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MeetingsViewModel(private val repository: Repository): ViewModel() {

    private val _meetingsList: MutableStateFlow<List<Meeting>> = MutableStateFlow(emptyList())
    fun getMeetingsListFlow(): StateFlow<List<Meeting>> = _meetingsList.asStateFlow()

    init {
        getMeetingsList()
    }

    fun getMeetingsList() {
        viewModelScope.launch {
            repository.getMeetingList().collect { meetingList ->
                _meetingsList.update { meetingList }
            }
        }
    }
}