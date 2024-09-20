package com.turitsynanton.android.wbtech.uinew.screens.eventslist

import android.os.Build
import android.util.Log
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Notifications
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
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.turitsynanton.android.wbtech.uinew.utils.EventCardStyles
import com.turitsynanton.android.wbtech.uinew.utils.SubscribeButtonStyle
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
    onSubscribeClick: () -> Unit,
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
    /*coroutineScope.launch {
        snapshotFlow { scrollState.firstVisibleItemIndex }
            .collect { currentScrollIndex ->
                // Проверка направления прокрутки
                if (currentScrollIndex < previousScrollIndex) {
                    // Прокрутка вверх — показать кнопку
                    showButton = true
                } else if (currentScrollIndex > previousScrollIndex) {
                    // Прокрутка вниз — скрыть кнопку
                    showButton = false
                }
                previousScrollIndex = currentScrollIndex
            }
    }*/
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
                    query = screenState.searchQuery,
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
                        ) { onProfileClick() }
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
                if (screenState.searchQuery.isEmpty()) {
                    items(FIRST_ITEMS_IN_COLUMN) { index ->
                        when (index) {
                            0 -> {
                                LazyRow(
                                    modifier = Modifier,
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    items(screenState.events.size) { index ->
                                        EventCard(
                                            modifier = Modifier
                                                .then(
                                                    when (index) {
                                                        0 -> Modifier.padding(start = 16.dp)
                                                        screenState.events.size - 1 -> Modifier.padding(
                                                            end = 16.dp
                                                        )

                                                        else -> Modifier
                                                    }
                                                ),
                                            eventId = screenState.events[index].id,
                                            eventName = screenState.events[index].name,
                                            eventDate = screenState.events[index].date,
                                            eventAddress = screenState.events[index].address,
                                            eventTags = screenState.events[index].tags,
                                            eventImage = screenState.events[index].image,
                                            eventStyle = EventCardStyles.Large,
                                        ) {
                                            onEventClick(screenState.events[index].id)
                                        }
                                    }
                                }
//                            CountryDropDown(modifier = Modifier)
                            }

                            1 -> {
                                DifferentEvents(
                                    modifier = Modifier,
                                    componentName = stringResource(id = R.string.upcoming_events),
                                    eventsList = screenState.upcomingEvents,
                                    onEventClick = onEventClick
                                )
                            }

                            2 -> {
                                CommunityRecommends(
                                    modifier = Modifier,
                                    recommendationName = "Сообщества для Вас",
                                    communitiesList = screenState.communities,
                                    subscribeButtonStyle = SubscribeButtonStyle.Default,
                                    onSubscribeButtonClick = { },
                                    onElementClick = { communityId ->
                                        onCommunityClick(communityId)
                                    }
                                )
                            }

                            3 -> {
                                OtherEvents(
                                    modifier = Modifier,
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
                items(screenState.filteredEvents.size) { index ->
                    EventCard(
                        modifier = Modifier,
                        eventId = screenState.filteredEvents[index].id,
                        eventName = screenState.filteredEvents[index].name,
                        eventDate = screenState.filteredEvents[index].date,
                        eventAddress = screenState.filteredEvents[index].address,
                        eventTags = screenState.filteredEvents[index].tags,
                        eventImage = screenState.filteredEvents[index].image,
                        eventStyle = EventCardStyles.Full,
                        onClick = onEventClick
                    )
                }
            }

            if (showButton) {
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
