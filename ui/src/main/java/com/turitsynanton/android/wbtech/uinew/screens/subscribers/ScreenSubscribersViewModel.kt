package com.turitsynanton.android.wbtech.uinew.screens.subscribers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turitsynanton.android.wbtech.domain.newusecases.subscribers.IGetSubscribersListUseCase
import com.turitsynanton.android.wbtech.models.UiPersonCard
import com.turitsynanton.android.wbtech.models.mapper.PersonCardMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class ScreenSubscribersViewModel(
    communityId: String,
    private val personCardMapper: PersonCardMapper,
    private val getSubscribersListUseCase: IGetSubscribersListUseCase
): ViewModel() {
    private val _subscribersList: MutableStateFlow<List<UiPersonCard>> = MutableStateFlow(emptyList())
    private val subscribersList: StateFlow<List<UiPersonCard>> = _subscribersList

    init {
        getParticipantsList(communityId)
    }

    fun getSubscribersListFlow(): StateFlow<List<UiPersonCard>> = subscribersList

    private fun getParticipantsList(communityId: String) {
        viewModelScope.launch {
            getSubscribersListUseCase.execute(communityId).collect { list ->
                _subscribersList.update {
                    list.map { personCardMapper.mapToUi(it) }
                }
            }
        }
    }
}