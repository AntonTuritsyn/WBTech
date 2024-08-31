package com.turitsynanton.android.wbtech.uinew.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.data.mocks.generateCommunitiesList
import com.turitsynanton.android.wbtech.data.mocks.generateEvents
import com.turitsynanton.android.wbtech.data.mocks.tags
import com.turitsynanton.android.wbtech.data.storage.newmodels.DataCommunity
import com.turitsynanton.android.wbtech.data.storage.newmodels.DataEvent
import com.turitsynanton.android.wbtech.uinew.components.CommunityRecommends
import com.turitsynanton.android.wbtech.uinew.components.DifferentEvents
import com.turitsynanton.android.wbtech.uinew.components.EventCard
import com.turitsynanton.android.wbtech.uinew.components.OtherEvents
import com.turitsynanton.android.wbtech.uinew.components.SearchFieldNew
import com.turitsynanton.android.wbtech.uinew.newviewmodel.ScreenEventsListViewModel
import com.turitsynanton.android.wbtech.uinew.utils.EventCardStyles
import com.turitsynanton.android.wbtech.uinew.utils.SubscribeButtonStyle
import com.turitsynanton.android.wbtech.uinew.utils.TagsStyle
import okhttp3.internal.filterList
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun ScreenEventsList(
    modifier: Modifier = Modifier,
    eventsUpcomingList: List<DataEvent>,
    screenEventsListViewModel: ScreenEventsListViewModel = koinViewModel(),
    onProfileClick: () -> Unit,
    onEventClick: () -> Unit,
    onCommunityClick: () -> Unit,
    onSubscribeClick: () -> Unit,
    onUserClick: () -> Unit
) {

    val eventTopList by screenEventsListViewModel.getEventsListFlow().collectAsStateWithLifecycle()
    val filteredEventsList by screenEventsListViewModel.getFilteredListFlow().collectAsStateWithLifecycle()
    val communitiesList by screenEventsListViewModel.getCommunitiesListFlow().collectAsStateWithLifecycle()
    val searchQuery by screenEventsListViewModel.getSearchQueryFlow().collectAsStateWithLifecycle()

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 20.dp, start = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                Log.d("TAG", "ScreenEventsList: $filteredEventsList")
                SearchFieldNew(
                    modifier = Modifier
                        .weight(1f)
                        .focusRequester(focusRequester),
                    query = searchQuery,
                    onQueryChanged = { screenEventsListViewModel.updateSearchQuery(it) }
                ) {
                    screenEventsListViewModel.updateSearchQuery("")
                    focusManager.clearFocus()
                }
                Image(
                    painter = painterResource(id = R.drawable.ic_user_new),
                    contentDescription = "",
                    modifier = Modifier
                        .clickable { onProfileClick() }
                )
            }
        }
    ) {
        LazyColumn(
            modifier = modifier
                .padding(it)
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            if (searchQuery.isEmpty()) {
                items(4) { index ->
                    when (index) {
                        0 -> {
                            LazyRow(
                                modifier = Modifier,
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                items(eventTopList.size) { index ->
                                    EventCard(
                                        modifier = Modifier,
                                        eventName = eventTopList[index].name,
                                        eventDate = eventTopList[index].date,
                                        eventAddress = eventTopList[index].city,
                                        eventTags = eventTopList[index].tags,
                                        eventStyle = EventCardStyles.Large
                                    ) {
                                        onEventClick()
                                    }
                                }
                            }
                        }

                        1 -> {
                            DifferentEvents(
                                modifier = Modifier,
                                componentName = stringResource(id = R.string.upcoming_events),
                                eventsList = eventTopList
                            ) {
                                onEventClick()
                            }
                        }

                        2 -> {
                            CommunityRecommends(
                                modifier = Modifier,
                                recommendationName = "Сообщества для тестировщиков",
                                communitiesList = communitiesList,
                                subscribeButtonStyle = SubscribeButtonStyle.Default,
                                onButtonClick = {  }
                            ) {
                                onCommunityClick()
                            }
                        }

                        3 -> {
                            OtherEvents(
                                modifier = Modifier,
                                tagsList = tags.map { tag ->
                                    tag to if (screenEventsListViewModel.isTagSelected(tag)) TagsStyle.Selected else TagsStyle.Unselected
                                }
                            ) { tag ->
                                screenEventsListViewModel.onTagSelected(tag)
                            }
                        }
                    }
                }
            }
            items(filteredEventsList.size) { index ->
                EventCard(
                    modifier = Modifier,
                    eventName = filteredEventsList[index].name,
                    eventDate = filteredEventsList[index].date,
                    eventAddress = filteredEventsList[index].city,
                    eventTags = filteredEventsList[index].tags,
                    eventStyle = EventCardStyles.Full
                ) {
                    onEventClick()
                }
            }
        }
    }
}
/*

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun ScreenEventsPreview() {
    ScreenEventsList(
        eventsList = generateEvents(),
//        eventTopList = generateEvents(),
        communitiesList = generateCommunitiesList(),
        eventsUpcomingList = generateEvents(),
        onEventClick = {},
        onCommunityClick = {},
        onProfileClick = {},
        onSubscribeClick = {},
    ) {

    }
}*/
