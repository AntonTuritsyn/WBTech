package com.turitsynanton.android.wbtech.uinew.screens.userprofile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.models.UiPerson
import com.turitsynanton.android.wbtech.uinew.components.CommunityRecommends
import com.turitsynanton.android.wbtech.uinew.components.DifferentEvents
import com.turitsynanton.android.wbtech.uinew.components.TopBar
import com.turitsynanton.android.wbtech.uinew.items.SimpleTextField
import com.turitsynanton.android.wbtech.uinew.items.SocialButton
import com.turitsynanton.android.wbtech.uinew.items.Tag
import com.turitsynanton.android.wbtech.uinew.utils.SubscribeButtonStyle
import com.turitsynanton.android.wbtech.uinew.utils.TagsStyle
import com.turitsynanton.android.wbtech.uinew.utils.TopBarStyles
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun ScreenProfileUser(
    modifier: Modifier = Modifier,
    userId: String,
    profileUserViewModel: ScreenProfileUserViewModel = koinViewModel(parameters = {
        parametersOf(userId)
    }),
    onBackClick: () -> Unit,
    onEventClick: (String) -> Unit,
    onCommunityClick: (String) -> Unit,
    onLogOutClick: () -> Unit
) {
    val userInfo by profileUserViewModel.getUserInfoFlow().collectAsStateWithLifecycle()
    val events by profileUserViewModel.getEventsFlow().collectAsStateWithLifecycle()
    val communities by profileUserViewModel.getCommunitiesFlow().collectAsStateWithLifecycle()

    Scaffold {
        LazyColumn(
            modifier = modifier
                .padding(it)
                .padding(bottom = 16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            item {
                userInfo?.let { user ->
                    MainInfo(user = user) {
                        onBackClick()
                    }
                }
            }
            item {
                DifferentEvents(
                    modifier = Modifier
                        .fillMaxWidth(),
                    componentName = stringResource(R.string.profile_events),
                    eventsList = events
                ) { event ->
                    onEventClick(event)
                }
            }
            item {
                CommunityRecommends(
                    modifier = Modifier
                        .fillMaxWidth(),
                    recommendationName = stringResource(R.string.profile_communities),
                    communitiesList = communities,
                    subscribeButtonStyle = SubscribeButtonStyle.Default,
                    onSubscribeButtonClick = { /*TODO*/ })
                { community ->
                    onCommunityClick(community)
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun MainInfo(
    modifier: Modifier = Modifier,
    user: UiPerson,
    onBackClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            if (user.avatar == "profile") {
                Image(
                    modifier = Modifier
                        .fillMaxWidth(),
                    painter = painterResource(id = R.drawable.my_photo),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
            } else {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(user.avatar)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
            }


            TopBar(
                modifier = Modifier
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Black.copy(alpha = 0.6f), Color.Transparent)
                        )
                    ),
                topBarColor = Color.Transparent,
                title = "",
                topBarStyle = TopBarStyles.Share,
                onIconClick = { }) {
                onBackClick()
            }
        }
        Spacer(modifier = Modifier.padding(10.dp))
        SimpleTextField(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            text = user.name,
            fontSize = 48.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF29183B)
        )
//        Spacer(modifier = Modifier.padding(4.dp))
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
            color = Color(0xFF000000),
            lineHeight = 18.sp
        )
        Spacer(modifier = Modifier.padding(9.dp))
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
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
            SocialButton(modifier = Modifier, icon = painterResource(id = R.drawable.ic_habr)) {

            }
            SocialButton(modifier = Modifier, icon = painterResource(id = R.drawable.ic_telegram)) {

            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun ScreenProfilePreview() {
    ScreenProfileUser(
        userId = "1",
        onBackClick = {},
        onEventClick = {},
        onCommunityClick = {}
    ) {}
}