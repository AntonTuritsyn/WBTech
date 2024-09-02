package com.turitsynanton.android.wbtech.uinew.screens.eventdetails

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.data.mocks.generateCommunity
import com.turitsynanton.android.wbtech.data.mocks.generateEvents
import com.turitsynanton.android.wbtech.data.mocks.generateHost
import com.turitsynanton.android.wbtech.data.mocks.generateTags
import com.turitsynanton.android.wbtech.data.storage.newmodels.DataEvent
import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay
import com.turitsynanton.android.wbtech.uinew.components.AddressCard
import com.turitsynanton.android.wbtech.uinew.components.DifferentEvents
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
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalComposeUiApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun ScreenEventDetails(
    modifier: Modifier = Modifier,
    eventId: String,
    eventDetailsViewModel: ScreenEventDetailsViewModel = koinViewModel(parameters = {
        parametersOf(
            eventId
        )
    }),
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
    onSubscribeClick: () -> Unit,
    onHostClick: () -> Unit,
    onParticipantsClick: () -> Unit,
    onOganizerClick: () -> Unit,
    onEventClick: () -> Unit,
    onSignUpToEventClick: () -> Unit
) {

    val eventDetails by eventDetailsViewModel.getEventDetailsFlow().collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopBar(
                modifier = Modifier,
                title = "${eventDetails?.name}",
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
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            item {
                eventDetails?.let { event ->
                    Header(event = event)
                }
            }
            item {
                SimpleTextField(
                    modifier = Modifier,
                    text = "${eventDetails?.description}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF000000)
                )
            }
            item {
                eventDetails?.let { event ->
                    Host(modifier = Modifier, host = event.host) {
                        onHostClick()
                    }
                }
            }
            item {
                AddressCard(
                    modifier = Modifier/*.pointerInteropFilter {
                    // Consume the touch event
                    true
                }*/, address = "Севкабель Порт, Кожевенная линия, 40, "
                )
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
                    community = generateCommunity(), // TODO: add community
                    onButtonClick = { onSubscribeClick() }) {
                    onOganizerClick()
                }
            }
            item {
                DifferentEvents(
                    modifier = Modifier,
                    componentName = "Другие встречи сообщества",
                    eventsList = listOf()
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
    event: DomainEvent
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
            event.tags.forEach { content ->
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