package com.turitsynanton.android.wbtech.ui.screens.additionalscreens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.turitsynanton.android.wbtech.R
import com.turitsynanton.android.wbtech.navigation.topbars.TobBarAdditionalScreens
import com.turitsynanton.android.wbtech.ui.components.MeetingCard
import com.turitsynanton.android.wbtech.ui.items.SomeText
import com.turitsynanton.android.wbtech.ui.screens.viewmodels.CommunityDetailsViewModel
import com.turitsynanton.android.wbtech.ui.screens.viewmodels.MeetingsViewModel
import com.turitsynanton.android.wbtech.ui.theme.NeutralLine
import com.turitsynanton.android.wbtech.ui.theme.NeutralWeak
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenCommunityDetails(
    modifier: Modifier = Modifier,
    communityDetailsViewModel: CommunityDetailsViewModel = koinViewModel(),
    meetingsViewModel: MeetingsViewModel = koinViewModel(),
    navController: NavHostController,
    onBackPressed: () -> Unit
) {
    val meetingsList by meetingsViewModel.getMeetingsListFlow().collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            TobBarAdditionalScreens("Designa", navController, onBackPressed)
        }
    ) {
        LazyColumn(
            modifier = modifier
                .padding(it)
                .padding(top = 16.dp)
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
        ) {
            item {
                SomeText(
                    modifier = Modifier
                        .height(270.dp),
                    text = stringResource(id = R.string.loremIpsum),
                    fontFamily = SfProDisplay,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                    color = NeutralWeak
                )
            }
            item {
                SomeText(
                    modifier = Modifier
                        .padding(top = 30.dp, bottom = 16.dp),
                    text = "Встречи сообщества",
                    fontFamily = SfProDisplay,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Normal,
                    color = NeutralWeak
                )
            }
            items(meetingsList) { item ->
                MeetingCard(
                    modifier = Modifier,
                    resId = R.drawable.ic_meeting,
                    meetingName = item.name,
                    ended = item.ended,
                    meetingDate = item.date,
                    meetingCity = item.city,
                    meetingTags = item.tags
                )
                HorizontalDivider(
                    modifier = Modifier.padding(top = 12.dp),
                    thickness = 1.dp,
                    color = NeutralLine
                )
            }
        }
    }
}