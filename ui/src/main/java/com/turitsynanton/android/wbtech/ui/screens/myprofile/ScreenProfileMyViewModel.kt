package com.turitsynanton.android.wbtech.ui.screens.myprofile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turitsynanton.android.wbtech.domain.usecases.myprofile.IGetMyProfileUseCase
import com.turitsynanton.android.wbtech.domain.usecases.myprofile.toggles.IGetListsVisibilityUseCase
import com.turitsynanton.android.wbtech.domain.usecases.myprofile.toggles.ISetListsVisibilityUseCase
import com.turitsynanton.android.wbtech.domain.usecases.userprofile.IGetCommunitiesForUserUseCase
import com.turitsynanton.android.wbtech.domain.usecases.userprofile.IGetEventsForUserUseCase
import com.turitsynanton.android.wbtech.models.UiCommunityCard
import com.turitsynanton.android.wbtech.models.UiEventCard
import com.turitsynanton.android.wbtech.models.UiPerson
import com.turitsynanton.android.wbtech.models.mapper.CommunityCardMapper
import com.turitsynanton.android.wbtech.models.mapper.EventCardMapper
import com.turitsynanton.android.wbtech.models.mapper.PersonMapper
import com.turitsynanton.android.wbtech.models.mapper.mapCommunityToUi
import com.turitsynanton.android.wbtech.models.mapper.mapEventCardToUi
import com.turitsynanton.android.wbtech.ui.state.ScreenMyProfileState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val COMMUNITIES_VISIBILITY_KEY = "COMMUNITIES_VISIBILITY_KEY"
private const val EVENTS_VISIBILITY_KEY = "EVENTS_VISIBILITY_KEY"
internal class ScreenProfileMyViewModel(
    private val userMapper: PersonMapper,
    private val eventCardMapper: EventCardMapper,
    private val communityCardMapper: CommunityCardMapper,
    private val getUserFullInfoUseCase: IGetMyProfileUseCase,
    private val getEventsForUserUseCase: IGetEventsForUserUseCase,
    private val getCommunitiesForUserUseCase: IGetCommunitiesForUserUseCase,
    private val setEventsVisibilityUseCase: ISetListsVisibilityUseCase,
    private val getEventsVisibilityUseCase: IGetListsVisibilityUseCase,
    private val setCommunitiesVisibilityUseCase: ISetListsVisibilityUseCase,
    private val getCommunitiesVisibilityUseCase: IGetListsVisibilityUseCase
): ViewModel() {
    private val _userInfo: MutableStateFlow<UiPerson?> = MutableStateFlow(null)
    private val userInfo: StateFlow<UiPerson?> = _userInfo.asStateFlow()

    private val _events: MutableStateFlow<List<UiEventCard>> = MutableStateFlow(emptyList())
    private val events: StateFlow<List<UiEventCard>> = _events.asStateFlow()

    private val _communities: MutableStateFlow<List<UiCommunityCard>> = MutableStateFlow(emptyList())
    private val communities: StateFlow<List<UiCommunityCard>> = _communities.asStateFlow()

    private val _isEditMode: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val isEditMode: StateFlow<Boolean> = _isEditMode.asStateFlow()

    private val _eventsIsVisible: MutableStateFlow<Boolean> = MutableStateFlow(true)
    private val eventsIsVisible: StateFlow<Boolean> = _eventsIsVisible.asStateFlow()

    private val _communitiesIsVisible: MutableStateFlow<Boolean> = MutableStateFlow(true)
    private val communitiesIsVisible: StateFlow<Boolean> = _communitiesIsVisible.asStateFlow()

    val myProfileScreenState: StateFlow<ScreenMyProfileState> = combine(
        getUserInfoFlow(),
        getEventsFlow(),
        getCommunitiesFlow(),
        getIsEditModeFlow(),
        getEventsIsVisibleFlow(),
        getCommunitiesIsVisibleFlow()
    ) { flows ->
        ScreenMyProfileState(
            user = flows.getOrNull(0) as UiPerson,
            eventsList = flows.getOrNull(1) as List<UiEventCard>,
            communitiesList = flows.getOrNull(2) as List<UiCommunityCard>,
            editMode = flows.getOrNull(3) as Boolean,
            isEventsVisible = flows.getOrNull(4) as Boolean,
            isCommunitiesVisible = flows.getOrNull(5) as Boolean
        )
    }.stateIn(viewModelScope, SharingStarted.Lazily, ScreenMyProfileState())

    init {
        getUserInfo()
        updateEventsIsVisible(EVENTS_VISIBILITY_KEY)
        updateCommunitiesIsVisible(COMMUNITIES_VISIBILITY_KEY)
    }

    fun changeEditMode() {
        _isEditMode.update { !isEditMode.value }
    }

    fun setCommunitiesIsVisible(key: String) {
        viewModelScope.launch {
            _communitiesIsVisible.update {
                !_communitiesIsVisible.value
            }
            setCommunitiesVisibilityUseCase.execute(key, _communitiesIsVisible.value)
        }
    }

    private fun getUserInfoFlow(): StateFlow<UiPerson?> = userInfo
    private fun getEventsFlow(): StateFlow<List<UiEventCard>> = events
    private fun getCommunitiesFlow(): StateFlow<List<UiCommunityCard>> = communities
    private fun getIsEditModeFlow(): StateFlow<Boolean> = isEditMode
    private fun getEventsIsVisibleFlow(): StateFlow<Boolean> = eventsIsVisible
    private fun getCommunitiesIsVisibleFlow(): StateFlow<Boolean> = communitiesIsVisible

    private fun getUserInfo() {
        viewModelScope.launch {
            getUserFullInfoUseCase.execute().collect { user ->
                _userInfo.update { userMapper.mapToUi(user) }
                Log.d("TAG", "getUserInfo: ${_userInfo.value!!.id}")
                getEventsForUser(_userInfo.value!!.id)
                getCommunitiesForUser(_userInfo.value!!.id)
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

    private fun updateEventsIsVisible(key: String) {
        viewModelScope.launch {
            getEventsVisibilityUseCase.execute(key).collect { visibility ->
                _eventsIsVisible.update { visibility }
                Log.d("TAG", "updateEventsIsVisible: ${_eventsIsVisible.value}")
            }
        }
    }
    fun setEventsIsVisible(key: String) {
        viewModelScope.launch {
            _eventsIsVisible.update {
                !_eventsIsVisible.value
            }
            Log.d("TAG", "setEventsIsVisible: ${_eventsIsVisible.value}")
            setEventsVisibilityUseCase.execute(key, _eventsIsVisible.value)
        }
    }

    private fun updateCommunitiesIsVisible(key: String) {
        viewModelScope.launch {
            getCommunitiesVisibilityUseCase.execute(key).collect { visibility ->
                _communitiesIsVisible.update { visibility }
            }
        }
    }

}
