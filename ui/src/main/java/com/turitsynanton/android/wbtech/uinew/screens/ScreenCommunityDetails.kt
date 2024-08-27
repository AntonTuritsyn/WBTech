package com.turitsynanton.android.wbtech.uinew.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.data.mocks.generateCommunity
import com.turitsynanton.android.wbtech.data.mocks.generateEvents
import com.turitsynanton.android.wbtech.data.storage.newmodels.DataCommunity
import com.turitsynanton.android.wbtech.data.storage.newmodels.DataEvent
import com.turitsynanton.android.wbtech.uinew.components.CommunityLargeCard
import com.turitsynanton.android.wbtech.uinew.components.DifferentEvents
import com.turitsynanton.android.wbtech.uinew.components.EventCard
import com.turitsynanton.android.wbtech.uinew.components.Subscribers
import com.turitsynanton.android.wbtech.uinew.components.TopBar
import com.turitsynanton.android.wbtech.uinew.components.avatars
import com.turitsynanton.android.wbtech.uinew.items.GradientButton
import com.turitsynanton.android.wbtech.uinew.items.SimpleTextField
import com.turitsynanton.android.wbtech.uinew.utils.ButtonStyle
import com.turitsynanton.android.wbtech.uinew.utils.EventCardStyles
import com.turitsynanton.android.wbtech.uinew.utils.SubscribeButtonStyle

@Composable
internal fun ScreenCommunityDetails(
    modifier: Modifier = Modifier,
    communityId: String,
    community: DataCommunity,
    eventList: List<DataEvent>,
    pastEventList: List<DataEvent>,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
    onSubscribeClick: () -> Unit,
    onUsersClick: () -> Unit,
    onEventClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                modifier = Modifier,
                title = "${community.name}",
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
                MainInfoBlock(
                    modifier = Modifier,
                    community = community,
                    onSubscribeClick = { onSubscribeClick() }
                ) {
                    onUsersClick()
                }
            }
            items(eventList.size + 1) { index ->
                when (index) {
                    0 -> {
                        SimpleTextField(
                            modifier = Modifier,
                            text = "Встречи",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF000000)
                        )
                    }

                    else -> {
                        EventCard(
                            modifier = Modifier,
                            eventName = eventList[index].name,
                            eventDate = eventList[index].name,
                            eventAddress = eventList[index].name,
                            eventTags = listOf(),
                            eventStyle = EventCardStyles.Full
                        ) {
                            onEventClick()
                        }
                    }
                }
            }
            item {
                PastEventsRow(modifier = Modifier, pastEventList = pastEventList) {
                    onEventClick()
                }
            }
        }
    }

}

@Composable
internal fun MainInfoBlock(
    modifier: Modifier,
    community: DataCommunity,
    onSubscribeClick: () -> Unit,
    onUsersClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        CommunityLargeCard(modifier = Modifier, community = community)
        Spacer(
            modifier = Modifier
                .padding(16.dp)
        )
        GradientButton(
            modifier = Modifier,
            text = "Подписаться",
            buttonStyle = ButtonStyle.Enable
        ) {
            onSubscribeClick()
        }
        Spacer(
            modifier = Modifier
                .padding(16.dp)
        )
        SimpleTextField(
            modifier = Modifier,
            text = "Сообщество профессионалов в сфере IT. Объединяем специалистов разных направлений для обмена опытом, знаниями и идеями.",
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
    pastEventList: List<DataEvent>,
    onEventClick: () -> Unit
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
            evetsTags = listOf()
        ) {
            onEventClick()
        }
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
        eventList = generateEvents(),
        pastEventList = generateEvents(),
        onBackClick = {},
        onShareClick = {},
        onSubscribeClick = {},
        onUsersClick = {}
    ) {
    }
}