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
import com.turitsynanton.android.wbtech.data.mocks.generateTagsForEvent
import com.turitsynanton.android.wbtech.data.storage.newmodels.DataEvent
import com.turitsynanton.android.wbtech.data.storage.newmodels.DataTag
import com.turitsynanton.android.wbtech.uinew.utils.EventCardStyles

@Composable
internal fun TopEvents(
    modifier: Modifier,
    eventsList: List<DataEvent>,
    evetsTags: List<DataTag>,
    onEventClick: () -> Unit
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
                val tagsList: List<DataTag> = generateTagsForEvent()
                EventCard(
                    modifier = Modifier,
                    eventName = eventsList[index].name,
                    eventDate = eventsList[index].date,
                    eventAddress = eventsList[index].city,
                    eventTags = tagsList,
                    eventStyle = EventCardStyles.Large
                ) {
                    onEventClick()
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun UpcomingEventsPreview() {
    TopEvents(modifier = Modifier, eventsList = generateEvents(), evetsTags = listOf()) {

    }
}