package com.turitsynanton.android.wbtech.ui.screens.communitydetails

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.models.UiCommunity
import com.turitsynanton.android.wbtech.models.UiEventCard
import com.turitsynanton.android.wbtech.ui.components.CommunityLargeCard
import com.turitsynanton.android.wbtech.ui.components.DifferentEvents
import com.turitsynanton.android.wbtech.ui.components.EventCard
import com.turitsynanton.android.wbtech.ui.components.Subscribers
import com.turitsynanton.android.wbtech.ui.components.TopBar
import com.turitsynanton.android.wbtech.ui.items.SimpleTextField
import com.turitsynanton.android.wbtech.ui.utils.EventCardStyles
import com.turitsynanton.android.wbtech.ui.utils.TopBarStyles
import com.turitsynanton.android.wbtech.ui.utils.buttonstates.CommunitySubscribeButtonState
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

private const val TAG = "ScreenCommunityDetails"

@Composable
internal fun ScreenCommunityDetails(
    modifier: Modifier = Modifier,
    communityId: String,
    screenCommunityDetailsViewModel: ScreenCommunityDetailsViewModel = koinViewModel(parameters = {
        parametersOf(
            communityId
        )
    }),
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
    onUsersClick: (String) -> Unit,
    onEventClick: (String) -> Unit
) {

    val screenState by screenCommunityDetailsViewModel.screenState
        .collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopBar(
                modifier = Modifier,
                title = "${screenState.communityDetails?.name}",
                topBarStyle = TopBarStyles.Share,
                onIconClick = { onShareClick() }
            ) {
                onBackClick()
            }
        }
    ) {
        LazyColumn(
            modifier = modifier
                .padding(it)
                .padding(bottom = 16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                screenState.communityDetails?.let { community ->
                    MainInfoBlock(
                        modifier = Modifier,
                        community = community,
                        subscribeStatus = screenState.buttonState,
                        subscribersCount = screenState.subscribersCount,
                        onSubscribeClick = { communityId ->
                            screenCommunityDetailsViewModel.subscribeToCommunity(communityId)
                        },
                        onUnsubscribeClick = { communityId ->
                            screenCommunityDetailsViewModel.unsubscribeFromCommunity(communityId)
                        },
                    ) {
                        onUsersClick(community.id)
                    }
                }
            }
            item {
                SimpleTextField(
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    text = stringResource(R.string.events_title_for_community),
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
                    eventImage = screenState.eventsList[index].image,
                    eventStyle = EventCardStyles.Full
                ) {
                    onEventClick(screenState.eventsList[index].id)
                }
            }
            item {
                PastEventsRow(
                    modifier = Modifier,
                    pastEventList = screenState.pastEventsList,
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
    subscribeStatus: CommunitySubscribeButtonState,
    subscribersCount: Int,
    onSubscribeClick: (String) -> Unit,
    onUnsubscribeClick: (String) -> Unit,
    onUsersClick: (String) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        CommunityLargeCard(
            modifier = Modifier,
            community = community,
            subscribeStatus = subscribeStatus,
            onUnsubscribeClick = { communityId ->
                onUnsubscribeClick(communityId)
            },
        )
        { communityId ->
            onSubscribeClick(communityId)
        }
        Spacer(
            modifier = Modifier
                .padding(16.dp)
        )
        SimpleTextField(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            text = community.description,
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
            usersCount = subscribersCount,
            title = stringResource(id = R.string.subscribers),
            avatarsList = community.users
        ) {
            onUsersClick(community.id)
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