package com.turitsynanton.android.wbtech.uinew.screens.communitydetails

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.data.mocks.generateCommunity
import com.turitsynanton.android.wbtech.data.mocks.generateEvents
import com.turitsynanton.android.wbtech.data.storage.newmodels.DataCommunity
import com.turitsynanton.android.wbtech.data.storage.newmodels.DataEvent
import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import com.turitsynanton.android.wbtech.models.UiCommunity
import com.turitsynanton.android.wbtech.models.UiEventCard
import com.turitsynanton.android.wbtech.uinew.components.CommunityLargeCard
import com.turitsynanton.android.wbtech.uinew.components.DifferentEvents
import com.turitsynanton.android.wbtech.uinew.components.EventCard
import com.turitsynanton.android.wbtech.uinew.components.Subscribers
import com.turitsynanton.android.wbtech.uinew.components.TopBar
import com.turitsynanton.android.wbtech.uinew.components.avatars
import com.turitsynanton.android.wbtech.uinew.items.GradientButton
import com.turitsynanton.android.wbtech.uinew.items.SimpleTextField
import com.turitsynanton.android.wbtech.uinew.screens.eventslist.ScreenEventsListViewModel
import com.turitsynanton.android.wbtech.uinew.utils.ButtonStyle
import com.turitsynanton.android.wbtech.uinew.utils.EventCardStyles
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun ScreenCommunityDetails(
    modifier: Modifier = Modifier,
    communityId: String,
    screenCommunityDetailsViewModel: ScreenCommunityDetailsViewModel = koinViewModel(parameters = {
        parametersOf(
            communityId
        )
    }),
    community: DataCommunity,
    pastEventList: List<UiEventCard>,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
    onSubscribeClick: () -> Unit,
    onUsersClick: () -> Unit,
    onEventClick: (String) -> Unit
) {

    val screenState by screenCommunityDetailsViewModel.screenState
        .collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            TopBar(
                modifier = Modifier,
                title = "${screenState.communityDetails?.name}",
                needActions = true,
                onShareClick = { onShareClick() }
            ) {
                onBackClick()
            }
        }
    ) {
        LazyColumn(
            modifier = modifier
                .padding(it)
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                screenState.communityDetails?.let { community ->
                    MainInfoBlock(
                        modifier = Modifier,
                        community = community,
                        onSubscribeClick = { onSubscribeClick() }
                    ) {
                        onUsersClick()
                    }
                }
            }
            item {
                SimpleTextField(
                    modifier = Modifier,
                    text = "Встречи",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF000000)
                )
            }
            items(screenState.eventsList.size) { index ->
                EventCard(
                    modifier = Modifier,
                    eventId = screenState.eventsList[index].id,
                    eventName = screenState.eventsList[index].name,
                    eventDate = screenState.eventsList[index].date,
                    eventAddress = screenState.eventsList[index].address,
                    eventTags = screenState.eventsList[index].tags,
                    eventStyle = EventCardStyles.Full
                ) {
                    onEventClick(screenState.eventsList[index].id)
                    Log.d("TAG", "onEventClick: ${screenState.eventsList[index].id}")
                }
            }

            item {
                PastEventsRow(
                    modifier = Modifier,
                    pastEventList = pastEventList,
                    onEventClick = onEventClick
                )
            }
        }
    }

}

@Composable
internal fun MainInfoBlock(
    modifier: Modifier,
    community: UiCommunity,
    onSubscribeClick: () -> Unit,
    onUsersClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        CommunityLargeCard(modifier = Modifier, community = community) {
            onSubscribeClick()
        }
        Spacer(
            modifier = Modifier
                .padding(16.dp)
        )
        SimpleTextField(
            modifier = Modifier,
            text = "${community.description}",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF000000)
        )
        Spacer(
            modifier = Modifier
                .padding(16.dp)
        )
        Subscribers(
            modifier = Modifier,
            title = stringResource(id = R.string.subscribers),
            avatarsList = avatars
        ) {
            onUsersClick()
        }
        Spacer(
            modifier = Modifier
                .padding(8.dp)
        )
    }
}

@Composable
internal fun PastEventsRow(
    modifier: Modifier,
    pastEventList: List<UiEventCard>,
    onEventClick: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.padding(8.dp))
        DifferentEvents(
            modifier = Modifier,
            componentName = stringResource(id = R.string.past_events),
            eventsList = pastEventList,
            onEventClick = onEventClick
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun ScreenCommunityPreview() {
    ScreenCommunityDetails(
        modifier = Modifier,
        communityId = "",
        community = generateCommunity(),
        pastEventList = listOf(),
        onBackClick = {},
        onShareClick = {},
        onSubscribeClick = {},
        onUsersClick = {}
    ) {
    }
}