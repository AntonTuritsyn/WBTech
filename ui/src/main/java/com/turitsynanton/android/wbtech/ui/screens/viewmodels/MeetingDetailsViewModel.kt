package com.turitsynanton.android.wbtech.ui.screens.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import com.turitsynanton.android.wbtech.domain.models.DomainMeeting
import com.turitsynanton.android.wbtech.domain.usecases.meeting.IGetMeetingDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MeetingDetailsViewModel(
    meetingId: String,
    private val iGetMeetingDetailsUseCase: IGetMeetingDetailsUseCase
): ViewModel() {
    private val _meeting: MutableStateFlow<DomainMeeting?> = MutableStateFlow(null)
    private val meeting: StateFlow<DomainMeeting?> = _meeting.asStateFlow()

    init {
        getMeetingDetails(meetingId)
    }

    fun getMeetingDetailsFlow(): StateFlow<DomainMeeting?> = meeting

    fun getMeetingDetails(meetingId: String) {
        viewModelScope.launch {
            iGetMeetingDetailsUseCase.execute(meetingId).collect { meetingDetails ->
                _meeting.update { meetingDetails }
            }
        }
    }
}