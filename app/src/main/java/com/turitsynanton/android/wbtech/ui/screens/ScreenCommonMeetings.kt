package com.turitsynanton.android.wbtech.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.turitsynanton.android.wbtech.navigation.pages
import com.turitsynanton.android.wbtech.ui.items.SearchField
import com.turitsynanton.android.wbtech.data.Meeting
import com.turitsynanton.android.wbtech.ui.organisms.MeetingTabContent
import com.turitsynanton.android.wbtech.ui.organisms.TabLayout

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScreenMeetings(modifier: Modifier, tabs: List<String>, meetingsList: List<Meeting>) {
        val pagerState = rememberPagerState(pageCount = { pages.size })
        Column(
            modifier = modifier
                .padding(horizontal = 24.dp)
        ) {
            SearchField(modifier = Modifier,true)
            TabLayout(tabsNames = tabs, pagerState = pagerState)
            MeetingTabContent(meetingsList = meetingsList, pagerState = pagerState, tabs)
        }
}



@Preview(showBackground = true)
@Composable
fun ScreenMeetingPreview() {
//    ScreenMeetings()
}