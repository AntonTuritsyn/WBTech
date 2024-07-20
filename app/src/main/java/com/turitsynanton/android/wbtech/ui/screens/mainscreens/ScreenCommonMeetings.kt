package com.turitsynanton.android.wbtech.ui.screens.mainscreens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.turitsynanton.android.wbtech.navigation.pages
import com.turitsynanton.android.wbtech.ui.items.SearchField
import com.turitsynanton.android.wbtech.navigation.topbars.TopBarMainScreens
import com.turitsynanton.android.wbtech.ui.organisms.MeetingTabContent
import com.turitsynanton.android.wbtech.ui.organisms.TabLayout
import com.turitsynanton.android.wbtech.ui.screens.viewmodels.MeetingsViewModel
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScreenMeetings(
    modifier: Modifier,
    tabs: List<String>,
    meetingsViewModel: MeetingsViewModel = koinViewModel(),
    onClick: () -> Unit
) {
    val meetingsList by meetingsViewModel.getMeetingsListFlow().collectAsState()

    Scaffold(
        topBar = {
            TopBarMainScreens(title = "Сообщества", false)
        }
    ) {
        val pagerState = rememberPagerState(pageCount = { pages.size })
        Column(
            modifier = modifier
                .padding(it)
                .padding(horizontal = 24.dp)
        ) {
            SearchField(modifier = Modifier, true)
            TabLayout(tabsNames = tabs, pagerState = pagerState)
            MeetingTabContent(meetingsList = meetingsList, pagerState = pagerState, tabs, onClick)
        }
    }
}