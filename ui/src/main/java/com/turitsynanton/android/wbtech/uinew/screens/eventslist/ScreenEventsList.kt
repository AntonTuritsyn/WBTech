package com.turitsynanton.android.wbtech.uinew.screens.eventslist

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.data.mocks.tags
import com.turitsynanton.android.wbtech.uinew.components.CommunityRecommends
import com.turitsynanton.android.wbtech.uinew.components.DifferentEvents
import com.turitsynanton.android.wbtech.uinew.components.EventCard
import com.turitsynanton.android.wbtech.uinew.components.OtherEvents
import com.turitsynanton.android.wbtech.uinew.components.SearchFieldNew
import com.turitsynanton.android.wbtech.uinew.state.EventsListState
import com.turitsynanton.android.wbtech.uinew.utils.EventCardStyles
import com.turitsynanton.android.wbtech.uinew.utils.TagsStyle
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel


private const val FIRST_ITEMS_IN_COLUMN = 4

@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun ScreenEventsList(
    modifier: Modifier = Modifier,
    screenEventsListViewModel: ScreenEventsListViewModel = koinViewModel(),
    onProfileClick: () -> Unit,
    onEventClick: (String) -> Unit,
    onCommunityClick: (String) -> Unit,
    onSubscribeClick: (String) -> Unit,
    onUserClick: () -> Unit
) {

    val screenState by screenEventsListViewModel.screenState.collectAsStateWithLifecycle()

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    var previousScrollIndex by remember { mutableIntStateOf(0) }
    var showButton by remember { mutableStateOf(false) }
    val firstVisibleItemScrollOffset by remember {
        derivedStateOf { scrollState.firstVisibleItemScrollOffset }
    }
    val firstVisibleItemIndex by remember {
        derivedStateOf { showButton && scrollState.firstVisibleItemIndex > 0 }
    }
    LaunchedEffect(firstVisibleItemScrollOffset) {
        val currentScrollIndex = scrollState.firstVisibleItemIndex
        val totalItemsCount = scrollState.layoutInfo.totalItemsCount
        val isLastItemVisible =
            scrollState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == totalItemsCount - 1
        if (currentScrollIndex < previousScrollIndex || isLastItemVisible) {
            showButton = true
        } else if (currentScrollIndex > previousScrollIndex) {
            showButton = false
        }
        previousScrollIndex = currentScrollIndex
    }
    when (screenState) {
        is EventsListState.Loading -> Unit
        is EventsListState.Error -> Unit
        is EventsListState.Loaded -> {
            Scaffold(
                topBar = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp, bottom = 20.dp, start = 16.dp, end = 16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        SearchFieldNew(
                            modifier = Modifier
                                .weight(1f)
                                .focusRequester(focusRequester),
                            query = (screenState as EventsListState.Loaded).searchQuery,
                            onQueryChanged = { screenEventsListViewModel.updateSearchQuery(it) }
                        ) {
                            screenEventsListViewModel.updateSearchQuery("")
                            focusManager.clearFocus()
                        }
                        Image(
                            painter = painterResource(id = R.drawable.ic_user_new),
                            contentDescription = "",
                            modifier = Modifier
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null
                                ) {
                                    onProfileClick()
                                }
                        )
                    }
                }
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    LazyColumn(
                        modifier = modifier
                            .padding(it)
                            .padding(bottom = 16.dp),
                        state = scrollState,
                        verticalArrangement = Arrangement.spacedBy(40.dp)
                    ) {
                        if ((screenState as EventsListState.Loaded).searchQuery.isEmpty()) {
                            items(FIRST_ITEMS_IN_COLUMN) { index ->
                                when (index) {
                                    0 -> {
                                        LazyRow(
                                            modifier = Modifier,
                                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                                        ) {
                                            items((screenState as EventsListState.Loaded).events.size) { index ->
                                                val event = (screenState as EventsListState.Loaded).events[index]
                                                EventCard(
                                                    modifier = Modifier
                                                        .then(
                                                            when (index) {
                                                                0 -> Modifier.padding(start = 16.dp)
                                                                (screenState as EventsListState.Loaded).events.size - 1 -> Modifier.padding(
                                                                    end = 16.dp
                                                                )
                                                                else -> Modifier
                                                            }
                                                        ),
                                                    eventId = event.id,
                                                    eventName = event.name,
                                                    eventDate = event.date,
                                                    eventAddress = event.address,
                                                    eventTags = event.tags,
                                                    eventImage = event.image,
                                                    eventStyle = EventCardStyles.Large,
                                                ) {
                                                    onEventClick((event.id))
                                                }
                                            }
                                        }
                                    }

                                    1 -> {
                                        DifferentEvents(
                                            modifier = Modifier,
                                            componentName = stringResource(id = R.string.upcoming_events),
                                            eventsList = (screenState as EventsListState.Loaded).upcomingEvents,
                                            onEventClick = onEventClick
                                        )
                                    }

                                    2 -> {
                                        CommunityRecommends(
                                            modifier = Modifier,
                                            recommendationName = stringResource(R.string.communities_for_you),
                                            communitiesList = (screenState as EventsListState.Loaded).communities,
                                            subscribeButtonStyle = /*SubscribeButtonStyle.Default*/ {
                                            },
                                            onSubscribeButtonClick = { communityId ->
                                                screenEventsListViewModel.subscribeToCommunity(communityId)
                                            },
                                            onElementClick = { communityId ->
                                                onCommunityClick(communityId)
                                            }
                                        )
                                    }

                                    3 -> {
                                        OtherEvents(
                                            modifier = Modifier,
                                            title = R.string.other_events,
                                            tagsList = tags.map { tag ->
                                                tag to if (screenEventsListViewModel.isTagSelected(tag)) {
                                                    TagsStyle.Selected
                                                } else {
                                                    TagsStyle.Unselected
                                                }
                                            }
                                        ) { tag ->
                                            screenEventsListViewModel.onTagSelected(tag)
                                        }
                                    }
                                }
                            }
                        }
                        items((screenState as EventsListState.Loaded).filteredEvents) { item ->
                            EventCard(
                                modifier = Modifier,
                                eventId = item.id,
                                eventName = item.name,
                                eventDate = item.date,
                                eventAddress = item.address,
                                eventTags = item.tags,
                                eventImage = item.image,
                                eventStyle = EventCardStyles.Full,
                                onClick = onEventClick
                            )
                        }
                    }

                    if (firstVisibleItemIndex) {
                        FloatingActionButton(
                            onClick = {
                                coroutineScope.launch {
                                    scrollState.animateScrollToItem(0)
                                }
                            },
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(16.dp),
                            shape = CircleShape,
                            containerColor = Color(0xFFF6F6FA)
                        ) {
                            Icon(imageVector = Icons.Rounded.KeyboardArrowUp, contentDescription = "Наверх")
                        }
                    }
                }
            }
        }
    }
}
