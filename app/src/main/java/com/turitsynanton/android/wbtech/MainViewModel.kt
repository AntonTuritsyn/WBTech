package com.turitsynanton.android.wbtech

import androidx.lifecycle.ViewModel
import com.turitsynanton.android.wbtech.data.Community
import com.turitsynanton.android.wbtech.data.communities
import com.turitsynanton.android.wbtech.data.meetings
import com.turitsynanton.android.wbtech.ui.screens.screenstate.CommunitiesScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel: ViewModel() {
    private val allMeetings = meetings
    private val allCommunities = communities

    private val initialCommunitiesScreenState = CommunitiesScreenState.Communities(communities)

    private val _currentCommunitiesScreenState: MutableStateFlow<CommunitiesScreenState> = MutableStateFlow(initialCommunitiesScreenState)
    val currentCommunitiesScreenState: StateFlow<CommunitiesScreenState> = _currentCommunitiesScreenState.asStateFlow()

    private var savedCommunitiesScreenState: CommunitiesScreenState = initialCommunitiesScreenState

    fun openCommunityDetails(community: Community) {
        savedCommunitiesScreenState = _currentCommunitiesScreenState.value
        _currentCommunitiesScreenState.value = CommunitiesScreenState.CommunityDetails(community, meetings)
    }

    fun closeCommunityDetails() {
        _currentCommunitiesScreenState.value = savedCommunitiesScreenState
    }
}