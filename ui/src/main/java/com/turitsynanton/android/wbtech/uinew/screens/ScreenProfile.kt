package com.turitsynanton.android.wbtech.uinew.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
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
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.data.mocks.generateTags
import com.turitsynanton.android.wbtech.data.mocks.generateUsersList
import com.turitsynanton.android.wbtech.data.storage.newmodels.DataUser
import com.turitsynanton.android.wbtech.domain.newmodels.DomainCommunity
import com.turitsynanton.android.wbtech.domain.newmodels.DomainEvent
import com.turitsynanton.android.wbtech.uinew.components.CommunityRecommends
import com.turitsynanton.android.wbtech.uinew.components.DifferentEvents
import com.turitsynanton.android.wbtech.uinew.components.TopBar
import com.turitsynanton.android.wbtech.uinew.items.SimpleTextField
import com.turitsynanton.android.wbtech.uinew.items.SocialButton
import com.turitsynanton.android.wbtech.uinew.items.Tag
import com.turitsynanton.android.wbtech.uinew.utils.SubscribeButtonStyle
import com.turitsynanton.android.wbtech.uinew.utils.TagsStyle

@Composable
internal fun ScreenProfile(
    modifier: Modifier = Modifier,
    user: DataUser,
    eventsList: List<DomainEvent>,
    communitiesList: List<DomainCommunity>,
    onBackClick: () -> Unit,
    onEventClick: () -> Unit,
    onCommunityClick: () -> Unit,
    onLogOutClick: () -> Unit
) {
    val stroke = Stroke(
        width = 2f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    )
    Scaffold {
        LazyColumn(
            modifier = modifier
                .padding(it)
                .padding(bottom = 16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            item {
                MainInfo(user = user) {
                    onBackClick()
                }
            }
            item {
                DifferentEvents(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    componentName = stringResource(R.string.my_events),
                    eventsList = eventsList
                ) {
                    onEventClick()
                }
            }
            item {
                CommunityRecommends(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    recommendationName = stringResource(R.string.my_communities),
                    communitiesList = communitiesList,
                    subscribeButtonStyle = SubscribeButtonStyle.Done,
                    onButtonClick = { /*TODO*/ }) {
                    onCommunityClick()
                }
            }
            item {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .drawBehind {
                            drawRoundRect(color = Color(0xFF76778E), style = stroke)
                        }
                        .clickable {
                            onLogOutClick()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    SimpleTextField(
                        modifier = Modifier,
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun MainInfo(
    modifier: Modifier = Modifier,
    user: DataUser,
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
            Image(
                modifier = Modifier
                    .fillMaxWidth(),
                painter = painterResource(id = R.drawable.my_photo),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            TopBar(
                modifier = Modifier,
                topBarColor = Color.Transparent,
                title = "",
                needActions = true,
                onShareClick = { onBackClick() }) {

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
            color = Color(0xFF000000)
        )
        Spacer(modifier = Modifier.padding(9.dp))
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            val tags = generateTags(min = 4, max = 8)
            tags.forEach { tag ->
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
    ScreenProfile(
        user = generateUsersList().first(),
        eventsList = listOf(),
        communitiesList = /*generateCommunitiesList()*/listOf(),
        onBackClick = {},
        onEventClick = {},
        onCommunityClick = {}
    ) {}
}