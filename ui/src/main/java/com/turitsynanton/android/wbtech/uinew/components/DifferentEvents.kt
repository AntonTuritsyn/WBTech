package com.turitsynanton.android.wbtech.uinew.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.models.UiEventCard
import com.turitsynanton.android.wbtech.uinew.items.SimpleTextField
import com.turitsynanton.android.wbtech.uinew.utils.EventCardStyles

@Composable
internal fun DifferentEvents(
    modifier: Modifier,
    componentName: String,
    eventsList: List<UiEventCard>,
//    eventsTags: List<DomainTag>,
    onEventClick: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        SimpleTextField(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            text = componentName,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF000000)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        LazyRow(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(eventsList.size) { index ->
                EventCard(
                    modifier = Modifier.then(
                        when (index) {
                            0 -> Modifier.padding(start = 16.dp)
                            eventsList.size - 1 -> Modifier.padding(end = 16.dp)
                            else -> Modifier
                        }
                    ),
                    eventId = eventsList[index].id,
                    eventName = eventsList[index].name,
                    eventDate = eventsList[index].date,
                    eventAddress = eventsList[index].address,
                    eventTags = eventsList[index].tags,
                    eventImage = eventsList[index].image,
                    eventStyle = EventCardStyles.Medium,
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
    DifferentEvents(
        modifier = Modifier,
        componentName = stringResource(id = R.string.upcoming_events),
        eventsList = listOf(),
//        eventsTags = listOf()
    ) {

    }
}