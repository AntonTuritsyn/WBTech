package com.turitsynanton.android.wbtech.ui.screens.participants

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turitsynanton.android.wbtech.domain.usecases.participants.IGetParticipantsListUseCase
import com.turitsynanton.android.wbtech.models.UiPersonCard
import com.turitsynanton.android.wbtech.models.mapper.PersonCardMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class ScreenParticipantsViewModel(
    eventId: String,
    private val personCardMapper: PersonCardMapper,
    private val getParticipantsListUseCase: IGetParticipantsListUseCase
) : ViewModel() {
    private val _participantsList: MutableStateFlow<List<UiPersonCard>> = MutableStateFlow(emptyList())
    private val participantsList: StateFlow<List<UiPersonCard>> = _participantsList

    init {
        getParticipantsList(eventId)
    }

    fun getParticipantsListFlow(): StateFlow<List<UiPersonCard>> = participantsList

    private fun getParticipantsList(eventId: String) {
        viewModelScope.launch {
            getParticipantsListUseCase.execute(eventId).collect { list ->
                _participantsList.update {
                    list.map { personCardMapper.mapToUi(it) }
                }
            }
        }
    }
}