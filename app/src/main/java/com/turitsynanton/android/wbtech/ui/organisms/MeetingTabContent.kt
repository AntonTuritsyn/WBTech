package com.turitsynanton.android.wbtech.ui.organisms

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import com.turitsynanton.android.wbtech.navigation.MeetingPageTabs
import com.turitsynanton.android.wbtech.navigation.pages
import com.turitsynanton.android.wbtech.data.Meeting
import com.turitsynanton.android.wbtech.data.tabs2

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MeetingTabContent(meetingsList: List<Meeting>, pagerState: PagerState, tabs: List<String>, onClick: () -> Unit) {
    val mine = tabs == tabs2
    HorizontalPager(state = pagerState) { pageIndex ->
        if (mine) {
            when (pages[pageIndex]) {
                MeetingPageTabs.LeftTab ->
                    MeetingColumn(meetingsList = meetingsList.filter { it.ended == false }, onClick = onClick)

                MeetingPageTabs.RightTab ->
                    MeetingColumn(meetingsList = meetingsList.filter { it.ended == true }, onClick = onClick)
            }
        } else {
            when (pages[pageIndex]) {
                MeetingPageTabs.LeftTab ->
                    MeetingColumn(meetingsList = meetingsList, onClick = onClick)

                MeetingPageTabs.RightTab ->
                    MeetingColumn(meetingsList = meetingsList.filter { it.ended == false }, onClick = onClick)
            }
        }
    }
}