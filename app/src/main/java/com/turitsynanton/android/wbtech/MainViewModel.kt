package com.turitsynanton.android.wbtech

import androidx.lifecycle.ViewModel
import com.turitsynanton.android.wbtech.data.dataCommunities
import com.turitsynanton.android.wbtech.data.dataMeetings
import com.turitsynanton.android.wbtech.data.storage.models.DataCommunity
import com.turitsynanton.android.wbtech.data.storage.models.User
import com.turitsynanton.android.wbtech.ui.screens.screenstate.CommunitiesScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
//    пока не используется, мб вообще не будет
    private val allMeetings = dataMeetings
    private val allCommunities = dataCommunities

    private val _user: MutableStateFlow<User?> = MutableStateFlow(null)
    val user: StateFlow<User?> = _user.asStateFlow()


    private val initialCommunitiesScreenState = CommunitiesScreenState.Communities(dataCommunities)

    private val _currentCommunitiesScreenState: MutableStateFlow<CommunitiesScreenState> =
        MutableStateFlow(initialCommunitiesScreenState)
    val currentCommunitiesScreenState: StateFlow<CommunitiesScreenState> =
        _currentCommunitiesScreenState.asStateFlow()

    private var savedCommunitiesScreenState: CommunitiesScreenState = initialCommunitiesScreenState

    fun openCommunityDetails(dataCommunity: DataCommunity) {
        savedCommunitiesScreenState = _currentCommunitiesScreenState.value
        _currentCommunitiesScreenState.value = CommunitiesScreenState.CommunityDetails(
            dataCommunity,
            dataMeetings
        )
    }

    fun closeCommunityDetails() {
        _currentCommunitiesScreenState.value = savedCommunitiesScreenState
    }

    fun updateUser(user: User) {
//        need emit
        _user.value = user
    }
}