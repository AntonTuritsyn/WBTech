package com.turitsynanton.android.wbtech.uinew.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.turitsynanton.android.wbtech.data.mocks.generateEvents
import com.turitsynanton.android.wbtech.data.mocks.generateTags
import com.turitsynanton.android.wbtech.data.storage.newmodels.DataEvent
import com.turitsynanton.android.wbtech.data.storage.newmodels.DataTag
import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import com.turitsynanton.android.wbtech.models.UiEventCard
import com.turitsynanton.android.wbtech.uinew.utils.EventCardStyles

@Composable
internal fun TopEvents(
    modifier: Modifier,
    eventsList: List<UiEventCard>,
    onEventClick: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        LazyRow(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(eventsList.size) { index ->
                EventCard(
                    modifier = Modifier,
                    eventId = eventsList[index].id,
                    eventName = eventsList[index].name,
                    eventDate = eventsList[index].date,
                    eventAddress = eventsList[index].address,
                    eventTags = eventsList[index].tags,
                    eventStyle = EventCardStyles.Large,
                    onClick = onEventClick
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun UpcomingEventsPreview() {
    TopEvents(modifier = Modifier, eventsList = listOf()) {

    }
}