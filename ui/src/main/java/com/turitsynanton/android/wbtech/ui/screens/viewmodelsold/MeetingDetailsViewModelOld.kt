package com.turitsynanton.android.wbtech.ui.screens.viewmodelsold

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turitsynanton.android.wbtech.domain.models.DomainMeeting
import com.turitsynanton.android.wbtech.domain.usecases.meeting.IGetMeetingDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MeetingDetailsViewModelOld(
    meetingId: String,
    private val iGetMeetingDetailsUseCase: IGetMeetingDetailsUseCase
): ViewModel() {
    private val _meeting: MutableStateFlow<DomainMeeting?> = MutableStateFlow(null)
    private val meeting: StateFlow<DomainMeeting?> = _meeting.asStateFlow()

    private val _isExpanded: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val isExpanded: StateFlow<Boolean> = _isExpanded.asStateFlow()

    init {
        getMeetingDetails(meetingId)
    }

    fun getMeetingDetailsFlow(): StateFlow<DomainMeeting?> = meeting

    fun isExpandedFlow(): StateFlow<Boolean> = isExpanded

    fun getMeetingDetails(meetingId: String) {
        viewModelScope.launch {
            iGetMeetingDetailsUseCase.execute(meetingId).collect { meetingDetails ->
                _meeting.update { meetingDetails }
            }
        }
    }

    fun toggleExpanded() {
        _isExpanded.value = !_isExpanded.value
    }
}