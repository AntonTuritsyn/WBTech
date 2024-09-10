package com.turitsynanton.android.wbtech.uinew.screens.myprofile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.data.mocks.tags
import com.turitsynanton.android.wbtech.models.UiCommunityCard
import com.turitsynanton.android.wbtech.models.UiEventCard
import com.turitsynanton.android.wbtech.models.UiPerson
import com.turitsynanton.android.wbtech.models.UiProfile
import com.turitsynanton.android.wbtech.uinew.components.CommunityRecommends
import com.turitsynanton.android.wbtech.uinew.components.DifferentEvents
import com.turitsynanton.android.wbtech.uinew.components.TopBar
import com.turitsynanton.android.wbtech.uinew.items.ComplexTextField
import com.turitsynanton.android.wbtech.uinew.items.CustomSwitch
import com.turitsynanton.android.wbtech.uinew.items.SimpleTextField
import com.turitsynanton.android.wbtech.uinew.items.SocialButton
import com.turitsynanton.android.wbtech.uinew.items.Tag
import com.turitsynanton.android.wbtech.uinew.utils.SubscribeButtonStyle
import com.turitsynanton.android.wbtech.uinew.utils.TagsStyle
import com.turitsynanton.android.wbtech.uinew.utils.TextFieldStyle
import com.turitsynanton.android.wbtech.uinew.utils.TopBarStyles
import org.koin.androidx.compose.koinViewModel

private val COMMUNITIES_VISIBILITY_KEY = "COMMUNITIES_VISIBILITY_KEY"
private const val EVENTS_VISIBILITY_KEY = "EVENTS_VISIBILITY_KEY"
@Composable
internal fun ScreenProfileMy(
    modifier: Modifier = Modifier,
    profileUserViewModel: ScreenProfileMyViewModel = koinViewModel(),
    eventsList: List<UiEventCard>,
    communitiesList: List<UiCommunityCard>,
    onBackClick: () -> Unit,
    onEditClick: () -> Unit,
    onSaveClick: () -> Unit,
    onSocialClick: () -> Unit,
    onEventClick: () -> Unit,
    onCommunityClick: () -> Unit,
    onLogOutClick: () -> Unit
) {
    val userInfo by profileUserViewModel.getUserInfoFlow().collectAsStateWithLifecycle()
    val isEdit by profileUserViewModel.getIsEditModeFlow().collectAsStateWithLifecycle()
    val isEventsVisible by profileUserViewModel.getEventsIsVisibleFlow()
        .collectAsStateWithLifecycle()
    val isCommunitiesVisible by profileUserViewModel.getCommunitiesIsVisibleFlow()
        .collectAsStateWithLifecycle()

    Scaffold {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .padding(it)
                .padding(bottom = 16.dp)
        ) {
            item {
                userInfo?.let { user ->
                    PhotoBlock(
                        user = user,
                        isEdit = isEdit,
                        onBackClick = { onBackClick() },
                        onEditClick = { profileUserViewModel.changeEditMode() },
                        onSaveClick = { onSaveClick() }) {
                    }
                }
            }
            item {
                userInfo?.let { user ->
                    InfoBlock(
                        isEdit = isEdit,
                        isCommunitiesVisible = isCommunitiesVisible,
                        isEventsVisible = isEventsVisible,
                        user = user,
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
                if (!isEdit) {
                    ListsBlock(
                        modifier = Modifier
                            .padding(top = 40.dp),
                        isEdit = isEdit,
                        eventsList = eventsList,
                        communitiesList = communitiesList,
                        onEventClick = { onEventClick() },
                        onCommunityClick = { onCommunityClick() }
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
                    when (isEdit) {
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
    onChangePhoto: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(),
            painter = painterResource(id = R.drawable.my_photo),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
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
                    ) { onChangePhoto() }
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
                    onBackClick()
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
    onCommunitiesToggle: () -> Unit,
    onEventsToggle: () -> Unit,
    onNotificationToggle: () -> Unit,
    onSocialClick: () -> Unit
) {
    when (isEdit) {
        true -> {
            Column(
                modifier = Modifier
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
//        TODO доделать поле для добавление интересов
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
                modifier = Modifier
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
    onEventClick: () -> Unit,
    onCommunityClick: () -> Unit,
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
        ) {
            onEventClick()
        }
        Spacer(modifier = Modifier.padding(20.dp))
        CommunityRecommends(
            modifier = Modifier
                .fillMaxWidth(),
            recommendationName = stringResource(R.string.my_communities),
            communitiesList = communitiesList,
            subscribeButtonStyle = SubscribeButtonStyle.Done,
            onButtonClick = { /*TODO*/ }) {
            onCommunityClick()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun ScreenProfilePreview() {
    val user = UiPerson(
        id = "1",
        name = "Антон",
        city = "Москва",
        phone = "8-800-555-35-35",
        description = "Занимаюсь разрабокой интерфейсов в eCom. Учу HTML, CSS и JavaScript",
        tags = listOf(),
        avatar = ""
    )
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            SimpleTextField(
                modifier = Modifier,
                text = stringResource(R.string.show_my_communities),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF9A10F0)
            )
            Spacer(modifier = Modifier.weight(1f))
            CustomSwitch(checked = true, onCheckedChange = {})
        }
        PhotoBlock(
            user = user,
            isEdit = false,
            onBackClick = { /*TODO*/ },
            onEditClick = { /*TODO*/ },
            onSaveClick = { /*TODO*/ }) {

        }
        InfoBlock(
            user = user,
            isCommunitiesVisible = true,
            isEventsVisible = true,
            isEdit = false,
            onEventsToggle = {},
            onCommunitiesToggle = {},
            onNotificationToggle = {},
            onSocialClick = { /*TODO*/ }
        )
        Spacer(modifier = Modifier.padding(20.dp))
        ListsBlock(
            isEdit = false,
            eventsList = listOf(),
            communitiesList = listOf(),
            onEventClick = { /*TODO*/ },
            onCommunityClick = { /*TODO*/ }
        )
    }

    /*ScreenProfileMy(
        eventsList = listOf(),
        communitiesList = *//*generateCommunitiesList()*//*listOf(),
        onBackClick = {},
        onEventClick = {},
        onCommunityClick = {}
    ) {}*/
}