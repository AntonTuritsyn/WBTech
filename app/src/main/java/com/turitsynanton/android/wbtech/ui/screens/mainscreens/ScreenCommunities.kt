package com.turitsynanton.android.wbtech.ui.screens.mainscreens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.turitsynanton.android.wbtech.data.storage.models.Community
import com.turitsynanton.android.wbtech.navigation.topbars.TopBarMainScreens
import com.turitsynanton.android.wbtech.ui.items.SearchField
import com.turitsynanton.android.wbtech.ui.organisms.CommunityList
import com.turitsynanton.android.wbtech.ui.screens.viewmodels.CommunityViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScreenCommunities(
    modifier: Modifier,
    communityViewModel: CommunityViewModel = koinViewModel(),
    onClick: () -> Unit
) {
    val communityList by communityViewModel.getMeetingsListFlow().collectAsState()

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
            CommunityList(communityList = communityList) {
//                viewModel.openCommunityDetails(it)
                onClick()
            }
        }
    }
}