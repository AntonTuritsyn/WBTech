package com.turitsynanton.android.wbtech.uinew.screens.userprofile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turitsynanton.android.wbtech.domain.usecases.userprofile.IGetCommunitiesForUserUseCase
import com.turitsynanton.android.wbtech.domain.usecases.userprofile.IGetEventsForUserUseCase
import com.turitsynanton.android.wbtech.domain.usecases.userprofile.IGetUserFullInfoUseCase
import com.turitsynanton.android.wbtech.models.UiCommunityCard
import com.turitsynanton.android.wbtech.models.UiEventCard
import com.turitsynanton.android.wbtech.models.UiPerson
import com.turitsynanton.android.wbtech.models.mapper.CommunityCardMapper
import com.turitsynanton.android.wbtech.models.mapper.EventCardMapper
import com.turitsynanton.android.wbtech.models.mapper.PersonMapper
import com.turitsynanton.android.wbtech.models.mapper.mapCommunityToUi
import com.turitsynanton.android.wbtech.models.mapper.mapEventCardToUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class ScreenProfileUserViewModel(
    userId: String,
    private val personMapper: PersonMapper,
    private val eventCardMapper: EventCardMapper,
    private val communityCardMapper: CommunityCardMapper,
    private val getUserFullInfoUseCase: IGetUserFullInfoUseCase,
    private val getEventsForUserUseCase: IGetEventsForUserUseCase,
    private val getCommunitiesForUserUseCase: IGetCommunitiesForUserUseCase
): ViewModel() {
    private val _userInfo: MutableStateFlow<UiPerson?> = MutableStateFlow(null)
    private val userInfo: StateFlow<UiPerson?> = _userInfo.asStateFlow()

    private val _events: MutableStateFlow<List<UiEventCard>> = MutableStateFlow(emptyList())
    private val events: StateFlow<List<UiEventCard>> = _events.asStateFlow()

    private val _communities: MutableStateFlow<List<UiCommunityCard>> = MutableStateFlow(emptyList())
    private val communities: StateFlow<List<UiCommunityCard>> = _communities.asStateFlow()

    fun getUserInfoFlow(): StateFlow<UiPerson?> = userInfo
    fun getEventsFlow(): StateFlow<List<UiEventCard>> = events
    fun getCommunitiesFlow(): StateFlow<List<UiCommunityCard>> = communities

    init {
        getUserInfo(userId)
        getEventsForUser(userId)
        getCommunitiesForUser(userId)
    }

    private fun getUserInfo(userId: String) {
        viewModelScope.launch {
            getUserFullInfoUseCase.execute(userId).collect { userInfo ->
                _userInfo.update { userInfo?.let { it1 -> personMapper.mapToUi(it1) } }
            }
        }
    }

    private fun getEventsForUser(userId: String) {
        viewModelScope.launch {
            getEventsForUserUseCase.execute(userId).collect { events->
                Log.d("TAG", "getEventsForUser: $events")
                _events.update { events.mapEventCardToUi(eventCardMapper) }
            }
        }
    }

    private fun getCommunitiesForUser(userId: String) {
        viewModelScope.launch {
            getCommunitiesForUserUseCase.execute(userId).collect { communities->
                _communities.update { communities.mapCommunityToUi(communityCardMapper) }
            }
        }
    }
}