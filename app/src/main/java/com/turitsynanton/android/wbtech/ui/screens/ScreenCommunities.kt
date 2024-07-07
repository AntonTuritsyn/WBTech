package com.turitsynanton.android.wbtech.ui.screens

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.turitsynanton.android.wbtech.MainViewModel
import com.turitsynanton.android.wbtech.data.Community
import com.turitsynanton.android.wbtech.data.communities
import com.turitsynanton.android.wbtech.data.meetingTag
import com.turitsynanton.android.wbtech.data.meetings
import com.turitsynanton.android.wbtech.data.tabs1
import com.turitsynanton.android.wbtech.navigation.NavigationCommunityDetails
import com.turitsynanton.android.wbtech.navigation.NavigationItems
import com.turitsynanton.android.wbtech.navigation.NavigationMeetingDetails
import com.turitsynanton.android.wbtech.navigation.TopBarMainScreens
import com.turitsynanton.android.wbtech.ui.items.SearchField
import com.turitsynanton.android.wbtech.ui.organisms.CommunityList
import com.turitsynanton.android.wbtech.ui.screens.screenstate.CommunitiesScreenState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScreenCommunities(modifier: Modifier, communityList: List<Community>, onClick: () -> Unit) {
    val viewModel: MainViewModel = viewModel()
    val screenState = viewModel
        .currentCommunitiesScreenState
        .collectAsState(CommunitiesScreenState.Initial)

    when (screenState.value) {
        is CommunitiesScreenState.Communities -> {
            Scaffold(
                topBar = {
                    TopBarMainScreens(title = "Встречи", true)
                }
            ) {
                Column(
                    modifier = modifier
                        .padding(it)
                        .padding(horizontal = 24.dp)
                ) {
                    SearchField(modifier = Modifier, true)
                    CommunityList(communityList = (screenState.value as CommunitiesScreenState.Communities).community) {
                        viewModel.openCommunityDetails(it)
                    }
                }
            }
        }

        is CommunitiesScreenState.CommunityDetails -> {
            ScreenCommunityDetails(
                meetingsList = meetings,
                modifier = modifier,
                onBackPressed = { viewModel.closeCommunityDetails() })
            BackHandler {
                viewModel.closeCommunityDetails()
            }
        }
        else -> {}
    }
}