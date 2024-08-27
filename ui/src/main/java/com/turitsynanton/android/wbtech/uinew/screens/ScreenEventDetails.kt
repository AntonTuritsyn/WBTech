package com.turitsynanton.android.wbtech.uinew.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.data.mocks.generateCommunity
import com.turitsynanton.android.wbtech.data.mocks.generateEvents
import com.turitsynanton.android.wbtech.data.mocks.generateHost
import com.turitsynanton.android.wbtech.data.mocks.generateTagsForEvent
import com.turitsynanton.android.wbtech.data.storage.newmodels.DataEvent
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay
import com.turitsynanton.android.wbtech.uinew.components.AddressCard
import com.turitsynanton.android.wbtech.uinew.components.DifferentEvents
import com.turitsynanton.android.wbtech.uinew.components.EventCard
import com.turitsynanton.android.wbtech.uinew.components.Host
import com.turitsynanton.android.wbtech.uinew.components.Organizer
import com.turitsynanton.android.wbtech.uinew.components.Subscribers
import com.turitsynanton.android.wbtech.uinew.components.TopBar
import com.turitsynanton.android.wbtech.uinew.components.avatars
import com.turitsynanton.android.wbtech.uinew.items.GradientButton
import com.turitsynanton.android.wbtech.uinew.items.ImageHolder
import com.turitsynanton.android.wbtech.uinew.items.SimpleTextField
import com.turitsynanton.android.wbtech.uinew.items.Tag
import com.turitsynanton.android.wbtech.uinew.utils.ButtonStyle
import com.turitsynanton.android.wbtech.uinew.utils.EventCardStyles
import com.turitsynanton.android.wbtech.uinew.utils.TagsStyle

@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun ScreenEventDetails(
    modifier: Modifier = Modifier,
    eventId: String,
    event: DataEvent,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
    onSubscribeClick: () -> Unit,
    onHostClick: () -> Unit,
    onParticipantsClick: () -> Unit,
    onOganizerClick: () -> Unit,
    onEventClick: () -> Unit,
    onSignUpToEventClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                modifier = Modifier,
                title = "Как повышать грейд. Лекция Павла Хорикова",
                needActions = true,
                onShareClick = { onShareClick() }
            ) {
                onBackClick()
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            item {
                Header(event = event)
            }
            item {
                SimpleTextField(
                    modifier = Modifier,
                    text = "Узнайте, как расти в профессии, улучшать навыки и получать повышение. Практические советы и реальные кейсы.\n" +
                            "Павел поделится эффективными стратегиями карьерного роста и методикой развития профессиональных навыков в IT.",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF000000)
                )
            }
            item {
                Host(modifier = Modifier, host = generateHost()) {
                    onHostClick()
                }
            }
            item {
                AddressCard(modifier = Modifier, address = "Севкабель Порт, Кожевенная линия, 40, ")
            }
            item {
                Subscribers(
                    modifier = Modifier,
                    title = stringResource(id = R.string.participants),
                    avatarsList = avatars
                ) {
                    onParticipantsClick()
                }
            }
            item {
                Organizer(
                    modifier = Modifier,
                    community = generateCommunity(),
                    onButtonClick = { onSubscribeClick() }) {
                    onOganizerClick()
                }
            }
            item {
                DifferentEvents(
                    modifier = Modifier,
                    componentName = "Другие встречи сообщества",
                    eventsList = generateEvents(),
                    evetsTags = listOf()
                ) {
                    onEventClick()
                }
            }
            item {
                GradientButton(
                    modifier = Modifier,
                    text = "Записаться на встречу",
                    buttonStyle = ButtonStyle.Enable
                ) {
                    onSignUpToEventClick()
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun Header(
    modifier: Modifier = Modifier,
    event: DataEvent
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topEnd = 16.dp,
                    topStart = 16.dp,
                    bottomEnd = 8.dp,
                    bottomStart = 8.dp
                )
            )
    ) {
        ImageHolder(
            modifier = Modifier,
            image = painterResource(id = R.drawable.event_example),
            height = EventCardStyles.Full
        )
        Spacer(modifier = Modifier.padding(4.dp))
        SimpleTextField(
            modifier = Modifier,
            text = event.name,
            fontFamily = SfProDisplay,
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF000000),
            lineHeight = 36.sp,
            maxLines = 2
        )
        SimpleTextField(
            modifier = Modifier,
            text = "${event.date} · ${event.city}",
            fontFamily = SfProDisplay,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF76778E),
            maxLines = 1
        )
        Spacer(modifier = Modifier.padding(4.dp))
        FlowRow(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            val tags = generateTagsForEvent()
            tags.forEach { content ->
                Tag(modifier = Modifier, text = content.content, style = TagsStyle.Minimize) {
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun ScreenEventPreview() {
    ScreenEventDetails(
        modifier = Modifier,
        event = generateEvents().first(),
        eventId = "1",
        onBackClick = {},
        onShareClick = {},
        onSubscribeClick = {},
        onHostClick = {},
        onParticipantsClick = {},
        onOganizerClick = {},
        onEventClick = {}
    ) {}
}