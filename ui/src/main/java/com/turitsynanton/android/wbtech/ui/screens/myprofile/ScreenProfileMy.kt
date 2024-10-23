package com.turitsynanton.android.wbtech.ui.screens.myprofile

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.models.UiCommunityCard
import com.turitsynanton.android.wbtech.models.UiEventCard
import com.turitsynanton.android.wbtech.models.UiPerson
import com.turitsynanton.android.wbtech.ui.components.DifferentEvents
import com.turitsynanton.android.wbtech.ui.components.TopBar
import com.turitsynanton.android.wbtech.ui.items.ComplexTextField
import com.turitsynanton.android.wbtech.ui.items.CustomSwitch
import com.turitsynanton.android.wbtech.ui.items.SimpleTextField
import com.turitsynanton.android.wbtech.ui.items.SocialButton
import com.turitsynanton.android.wbtech.ui.items.Tag
import com.turitsynanton.android.wbtech.ui.utils.TagsStyle
import com.turitsynanton.android.wbtech.ui.utils.TextFieldStyle
import com.turitsynanton.android.wbtech.ui.utils.TopBarStyles
import org.koin.androidx.compose.koinViewModel

private const val COMMUNITIES_VISIBILITY_KEY = "COMMUNITIES_VISIBILITY_KEY"
private const val EVENTS_VISIBILITY_KEY = "EVENTS_VISIBILITY_KEY"

@Composable
internal fun ScreenProfileMy(
    modifier: Modifier = Modifier,
    profileUserViewModel: ScreenProfileMyViewModel = koinViewModel(),
    onBackClick: () -> Unit,
    onEditClick: () -> Unit,
    onSaveClick: () -> Unit,
    onChangePhoto: () -> Unit,
    onChangeInterests: () -> Unit,
    onSocialClick: () -> Unit,
    onEventClick: (String) -> Unit,
    onCommunityClick: (String) -> Unit,
    onLogOutClick: () -> Unit
) {

    val screenState by profileUserViewModel.myProfileScreenState.collectAsStateWithLifecycle()

    Scaffold {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .padding(it)
                .padding(bottom = 16.dp)
        ) {
            Log.d("TAG", "isEdit: $screenState.editMode")
            item {
                screenState.user?.let { user ->
                    PhotoBlock(
                        user = user,
                        isEdit = screenState.editMode,
                        onBackClick = { if (!screenState.editMode) { onBackClick() } },
                        onEditClick = { profileUserViewModel.changeEditMode() },
                        onCancelEdit = { profileUserViewModel.changeEditMode() },
                        onSaveClick = { onSaveClick() },
                        onChangePhoto = { onChangePhoto() }
                    )
                }
            }
            item {
                screenState.user?.let { user ->
                    InfoBlock(
                        isEdit = screenState.editMode,
                        isCommunitiesVisible = screenState.isCommunitiesVisible,
                        isEventsVisible = screenState.isEventsVisible,
                        user = user,
                        onChangeInterests = {
                            onChangeInterests()
                        },
                        onCommunitiesToggle = {
                            profileUserViewModel.setCommunitiesIsVisible(COMMUNITIES_VISIBILITY_KEY)
                        },
                        onEventsToggle = {
                            profileUserViewModel.setEventsIsVisible(EVENTS_VISIBILITY_KEY)
                        },
                        onNotificationToggle = {

                        }
                    ) {
                        onSocialClick()
                    }
                }
            }
            item {
                if (!screenState.editMode) {
                    ListsBlock(
                        modifier = Modifier
                            .padding(top = 40.dp),
                        isEdit = screenState.editMode,
                        eventsList = screenState.eventsList,
                        communitiesList = screenState.communitiesList,
                        onEventClick = { eventId ->
                            onEventClick(eventId)
                        },
                        onCommunityClick = { community ->
                            onCommunityClick(community)
                        }
                    )
                }
            }
            item {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    when (screenState.editMode) {
                        true -> {
                            SimpleTextField(
                                modifier = Modifier
                                    .clickable {

                                    },
                                text = stringResource(R.string.delete_profile),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFFFF0000)
                            )
                        }

                        false -> {
                            SimpleTextField(
                                modifier = Modifier
                                    .clickable {
                                        onLogOutClick()
                                    },
                                text = stringResource(R.string.log_out),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFF76778E)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
internal fun PhotoBlock(
    modifier: Modifier = Modifier,
    user: UiPerson,
    isEdit: Boolean,
    onBackClick: () -> Unit,
    onEditClick: () -> Unit,
    onSaveClick: () -> Unit,
    onCancelEdit: () -> Unit,
    onChangePhoto: () -> Unit
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(),
            painter = painterResource(id = R.drawable.my_photo),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
        /*AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://cdn1.ozone.ru/s3/multimedia-t/6365554865.jpg")
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )*/
        if (isEdit) {
            SimpleTextField(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp)
                    .background(
                        color = Color(0x4D9A10F0),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        onChangePhoto()
                    }
                    .padding(8.dp),
                text = stringResource(R.string.change_photo),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFFF6F6FA)
            )
        }
        when (isEdit) {
            false -> {
                TopBar(
                    modifier = Modifier
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color.Black.copy(alpha = 0.6f), Color.Transparent)
                            )
                        ),
                    topBarColor = Color.Transparent,
                    title = "",
                    topBarStyle = TopBarStyles.Edit,
                    onIconClick = { onEditClick() }
                )
                {
                    onBackClick()
                }
            }

            true -> {
                TopBar(
                    modifier = Modifier
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color.Black.copy(alpha = 0.6f), Color.Transparent)
                            )
                        ),
                    topBarColor = Color.Transparent,
                    title = "",
                    topBarStyle = TopBarStyles.Save,
                    onIconClick = { onSaveClick() }
                )
                {
                    onCancelEdit()
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun InfoBlock(
    modifier: Modifier = Modifier,
    isEdit: Boolean,
    user: UiPerson,
    isCommunitiesVisible: Boolean,
    isEventsVisible: Boolean,
    onChangeInterests: () -> Unit,
    onCommunitiesToggle: () -> Unit,
    onEventsToggle: () -> Unit,
    onNotificationToggle: () -> Unit,
    onSocialClick: () -> Unit
) {
    when (isEdit) {
        true -> {
            Column(
                modifier = modifier
                    .fillMaxWidth()
            ) {
                ComplexTextField(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 40.dp),
                    hint = stringResource(id = R.string.hint_name),
                    query = user.name,
                    onQueryChanged = {},
                    textFieldStyle = TextFieldStyle.Filled
                )
                ComplexTextField(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 8.dp),
                    hint = stringResource(id = R.string.phone_profile_placeholder),
                    query = user.phone,
                    onQueryChanged = {},
                    textFieldStyle = TextFieldStyle.Filled
                )
                ComplexTextField(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 8.dp),
                    hint = stringResource(id = R.string.city),
                    query = user.city,
                    onQueryChanged = {},
                    textFieldStyle = TextFieldStyle.Filled
                )
                ComplexTextField(
                    modifier = Modifier
                        .heightIn(min = 102.dp)
                        .padding(horizontal = 16.dp)
                        .padding(top = 8.dp),
                    hint = stringResource(id = R.string.talk_about_yourself),
                    query = user.description,
                    singleLine = false,
                    onQueryChanged = {},
                    textFieldStyle = TextFieldStyle.Filled
                )
//        интересы
                SimpleTextField(
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .padding(horizontal = 16.dp),
                    text = stringResource(R.string.interests),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF000000)
                )
//        TODO доделать поле для добавления интересов
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    user.tags.forEach { tag ->
                        Tag(
                            modifier = Modifier,
                            text = tag.content,
                            style = TagsStyle.UnclickableBigForProfileEdit
                        ) {

                        }
                    }
                    Tag(
                        modifier = Modifier,
                        text = stringResource(R.string.change_interests_profile),
                        style = TagsStyle.Unselected
                    ) {
                        onChangeInterests()
                    }
                }
//        соцсети
                SimpleTextField(
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .padding(horizontal = 16.dp),
                    text = stringResource(R.string.socials),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF000000)
                )
                ComplexTextField(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp),
                    hint = "Хабр",
                    query = "",
                    onQueryChanged = {},
                    textFieldStyle = TextFieldStyle.Filled
                )
                ComplexTextField(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 8.dp),
                    hint = "Телеграм",
                    query = "",
                    onQueryChanged = {},
                    textFieldStyle = TextFieldStyle.Filled
                )
//        toggles
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 40.dp)
                ) {
                    SimpleTextField(
                        modifier = Modifier,
                        text = stringResource(R.string.show_my_communities),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF9A10F0)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    CustomSwitch(
                        checked = isCommunitiesVisible,
                        onCheckedChange = { onCommunitiesToggle() }) // TODO ПЕРЕРАБОТАТЬ
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 18.dp)
                ) {
                    SimpleTextField(
                        modifier = Modifier,
                        text = stringResource(R.string.show_my_events),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF9A10F0)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    CustomSwitch(checked = isEventsVisible, onCheckedChange = { onEventsToggle() })
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 34.dp)
                ) {
                    SimpleTextField(
                        modifier = Modifier,
                        text = stringResource(R.string.notifications),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF9A10F0)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    CustomSwitch(checked = true, onCheckedChange = { onNotificationToggle() })
                }

            }
        }

        false -> {
            Column(
                modifier = modifier
                    .fillMaxWidth()
            ) {
                SimpleTextField(
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    text = user.name,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF29183B)
                )
                SimpleTextField(
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    text = user.city,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF000000),
                    lineHeight = 18.sp
                )
                SimpleTextField(
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    text = user.description,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 16.sp,
                    color = Color(0xFF000000)
                )
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    user.tags.forEach { tag ->
                        Tag(modifier = Modifier, text = tag.content, style = TagsStyle.Minimize) {

                        }
                    }
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SocialButton(
                        modifier = Modifier,
                        icon = painterResource(id = R.drawable.ic_habr)
                    ) {
                        onSocialClick()
                    }
                    SocialButton(
                        modifier = Modifier,
                        icon = painterResource(id = R.drawable.ic_telegram)
                    ) {
                        onSocialClick()
                    }
                }
            }
        }
    }
}

@Composable
internal fun ListsBlock(
    modifier: Modifier = Modifier,
    isEdit: Boolean,
    eventsList: List<UiEventCard>,
    communitiesList: List<UiCommunityCard>,
    onEventClick: (String) -> Unit,
    onCommunityClick: (String) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        DifferentEvents(
            modifier = Modifier
                .fillMaxWidth(),
            componentName = stringResource(R.string.my_events),
            eventsList = eventsList
        ) { eventId ->
            onEventClick(eventId)
        }
        Spacer(modifier = Modifier.padding(20.dp))
        /*CommunityRecommends(
            modifier = Modifier
                .fillMaxWidth(),
            recommendationName = stringResource(R.string.my_communities),
            communitiesList = communitiesList,
            subscribeButtonStyle = SubscribeButtonStyle.Done,
            onSubscribeButtonClick = { TODO }
        ) { communityId ->
            onCommunityClick(communityId)
        }*/
    }
}