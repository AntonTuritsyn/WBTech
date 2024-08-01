package com.turitsynanton.android.wbtech.ui.organisms

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import com.turitsynanton.android.wbtech.data.tabs2
import com.turitsynanton.android.wbtech.domain.models.DomainMeeting
import com.turitsynanton.android.wbtech.ui.MeetingPageTabs
import com.turitsynanton.android.wbtech.ui.pages

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MeetingTabContent(meetingsList: List<DomainMeeting>, pagerState: PagerState, tabs: List<String>, onClick: () -> Unit) {
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