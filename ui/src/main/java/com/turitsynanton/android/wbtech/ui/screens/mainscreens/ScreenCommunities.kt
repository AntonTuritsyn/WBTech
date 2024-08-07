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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.turitsynanton.android.wbtech.ui.items.SearchField
import com.turitsynanton.android.wbtech.ui.organisms.CommunityList
import com.turitsynanton.android.wbtech.ui.screens.viewmodels.CommunityViewModel
import com.turitsynanton.android.wbtech.ui.topbars.TopBarMainScreens
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun ScreenCommunities(
    modifier: Modifier = Modifier,
    communityViewModel: CommunityViewModel = koinViewModel(),
    onClick: (String) -> Unit
) {
    val communityList by communityViewModel.getMeetingsListFlow().collectAsStateWithLifecycle()

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
            CommunityList(
                domainCommunityList = communityList,
                onClick = onClick
            )
        }
    }
}