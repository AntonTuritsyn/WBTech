package com.turitsynanton.android.wbtech.uinew.screens.eventdetails

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.models.UiEvent
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay
import com.turitsynanton.android.wbtech.uinew.components.AddressCard
import com.turitsynanton.android.wbtech.uinew.components.DifferentEvents
import com.turitsynanton.android.wbtech.uinew.components.Host
import com.turitsynanton.android.wbtech.uinew.components.Organizer
import com.turitsynanton.android.wbtech.uinew.components.Subscribers
import com.turitsynanton.android.wbtech.uinew.components.TopBar
import com.turitsynanton.android.wbtech.uinew.items.FloatingCustomButton
import com.turitsynanton.android.wbtech.uinew.items.GradientButton
import com.turitsynanton.android.wbtech.uinew.items.ImageHolder
import com.turitsynanton.android.wbtech.uinew.items.SimpleTextField
import com.turitsynanton.android.wbtech.uinew.items.Tag
import com.turitsynanton.android.wbtech.uinew.utils.ButtonStyle
import com.turitsynanton.android.wbtech.uinew.utils.EventCardStyles
import com.turitsynanton.android.wbtech.uinew.utils.TagsStyle
import com.turitsynanton.android.wbtech.uinew.utils.TopBarStyles
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

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
    onSubscribeToCommunityClick: () -> Unit,
    onHostClick: (String) -> Unit,
    onParticipantsClick: (String) -> Unit,
    onOrganizerClick: (String) -> Unit,
    onEventClick: (String) -> Unit,
    onSignUpToEventClick: (String) -> Unit
) {
    val screenState by eventDetailsViewModel.screenState.collectAsStateWithLifecycle()

    val scrollState = rememberLazyListState()
    var previousScrollIndex by remember { mutableIntStateOf(0) }
//    var previousScrollOffset by remember { mutableStateOf(0f) }
    var showButton by remember { mutableStateOf(false) }
    val firstVisibleItemScrollOffset by remember {
        derivedStateOf { scrollState.firstVisibleItemScrollOffset }
    }
//    val hideButtonThreshold = 100.dp.value
    LaunchedEffect(firstVisibleItemScrollOffset) {
        val currentScrollIndex = scrollState.firstVisibleItemIndex
        val totalItemsCount = scrollState.layoutInfo.totalItemsCount
        val isLastItemVisible =
            scrollState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == totalItemsCount - 1
        val currentScrollOffset = scrollState.firstVisibleItemScrollOffset.toFloat()

        if (currentScrollIndex < previousScrollIndex/*currentScrollOffset < previousScrollOffset*/ || isLastItemVisible) {
            showButton = true
        } else if (currentScrollIndex > previousScrollIndex /*currentScrollOffset > hideButtonThreshold*/) {
            showButton = false
        }
        previousScrollIndex = currentScrollIndex
//        previousScrollOffset = currentScrollOffset
    }
    Scaffold(
        topBar = {
            TopBar(
                modifier = Modifier,
                title = "${screenState.eventDetails?.title}",
                topBarStyle = TopBarStyles.Share,
                onIconClick = { onShareClick() }
            ) {
                onBackClick()
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = modifier
                    .padding(it)
                    .padding(bottom = 16.dp),
                state = scrollState,
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                item {
                    screenState.eventDetails?.let { event ->
                        Header(event = event)
                    }
                }
                item {
                    SimpleTextField(
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        text = "${screenState.eventDetails?.description}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 16.sp,
                        color = Color(0xFF000000)
                    )
                }
                item {
                    screenState.eventDetails?.let { event ->
                        Host(modifier = Modifier, host = event.host) {
                            onHostClick(event.host.id)
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
                    screenState.participants.let { it1 ->
                        Subscribers(
                            modifier = Modifier,
                            title = stringResource(id = R.string.participants),
                            avatarsList = it1
                        ) {
                            screenState.eventDetails?.let { it2 -> onParticipantsClick(it2.id) }
                        }
                    }
                }
                item {
                    screenState.communityDetails?.let { community ->
                        Organizer(
                            modifier = Modifier,
                            community = community,
                            onButtonClick = { onSubscribeToCommunityClick() },
                            onElementClick = { communityId ->
                                onOrganizerClick(communityId)
                            })
                    }
                }
                item {
                    DifferentEvents(
                        modifier = Modifier
                            .padding(bottom = if (!screenState.buttonStatus) 116.dp else 0.dp),
                        componentName = "Другие встречи сообщества",
                        eventsList = screenState.otherEvents,
                        onEventClick = onEventClick
                    )
                }
            }
            if (showButton && !screenState.buttonStatus) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(
                            elevation = 32.dp,
                            spotColor = Color(0x33000000),
                            ambientColor = Color(0x33000000),
                            clip = false
                        )
                        .align(Alignment.BottomCenter)
                        .clip(shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                        .background(Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        SimpleTextField(
                            modifier = Modifier
                                .padding(bottom = 12.dp),
                            text = "Всего 30 мест. Если передумаете — отпишитесь",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            lineHeight = 16.sp,
                            color = Color(0xFF9A10F0)
                        )
                        FloatingCustomButton(
                            modifier = Modifier,
                            text = stringResource(R.string.start_registration_to_event),
                            buttonStyle = ButtonStyle.Enable
                        ) {
                            screenState.eventDetails?.let { event -> onSignUpToEventClick(event.id) }
                        }
                    }

                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun Header(
    modifier: Modifier = Modifier,
    event: UiEvent
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
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
            image = event.image,
            height = EventCardStyles.Details
        )
        Spacer(modifier = Modifier.padding(4.dp))
        SimpleTextField(
            modifier = Modifier,
            text = event.title,
            fontFamily = SfProDisplay,
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF000000),
            lineHeight = 36.sp
        )
        SimpleTextField(
            modifier = Modifier,
            text = "${event.date} · ${event.address}",
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
        onSubscribeToCommunityClick = {},
        onHostClick = {},
        onParticipantsClick = {},
        onOrganizerClick = {},
        onEventClick = {}
    ) {}
}