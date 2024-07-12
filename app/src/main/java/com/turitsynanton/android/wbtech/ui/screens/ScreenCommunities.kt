package com.turitsynanton.android.wbtech.ui.screens

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
import com.turitsynanton.android.wbtech.MainViewModel
import com.turitsynanton.android.wbtech.data.Community
import com.turitsynanton.android.wbtech.data.communities
import com.turitsynanton.android.wbtech.data.meetings
import com.turitsynanton.android.wbtech.navigation.topbars.TopBarMainScreens
import com.turitsynanton.android.wbtech.ui.items.SearchField
import com.turitsynanton.android.wbtech.ui.organisms.CommunityList
import com.turitsynanton.android.wbtech.ui.screens.screenstate.CommunitiesScreenState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScreenCommunities(modifier: Modifier, communityList: List<Community>, onClick: () -> Unit) {
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
            CommunityList(communityList = communityList/*(screenState.value as CommunitiesScreenState.Communities).community*/) {
//                viewModel.openCommunityDetails(it)
                onClick()
            }
        }
    }
}