package com.turitsynanton.android.wbtech.ui.organisms

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.turitsynanton.android.wbtech.R
import com.turitsynanton.android.wbtech.domain.models.DomainMeeting
import com.turitsynanton.android.wbtech.ui.components.MeetingCard
import com.turitsynanton.android.wbtech.ui.theme.NeutralLine

@Composable
fun MeetingColumn(meetingsList: List<DomainMeeting>, onClick: () -> Unit) {
    LazyColumn(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(meetingsList.size) { index ->
            MeetingCard(
                modifier = Modifier
                    .clickable { onClick() },
                resId = R.drawable.ic_meeting,
                meetingName = meetingsList[index].name,
                ended = meetingsList[index].ended,
                meetingDate = meetingsList[index].date,
                meetingCity = meetingsList[index].city,
                meetingTags = meetingsList[index].tags
            )
            HorizontalDivider(
                modifier = Modifier.padding(top = 12.dp),
                thickness = 1.dp,
                color = NeutralLine
            )
        }
    }
}
