package com.turitsynanton.android.wbtech.ui.screens.additionalscreens

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.ui.components.ExpandableText
import com.turitsynanton.android.wbtech.ui.components.MeetingCard
import com.turitsynanton.android.wbtech.ui.items.SomeText
import com.turitsynanton.android.wbtech.ui.screens.viewmodelsold.CommunityDetailsViewModelOld
import com.turitsynanton.android.wbtech.ui.screens.viewmodelsold.MeetingsViewModelOld
import com.turitsynanton.android.wbtech.ui.theme.NeutralLine
import com.turitsynanton.android.wbtech.ui.theme.NeutralWeak
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay
import com.turitsynanton.android.wbtech.ui.topbars.TobBarAdditionalScreens
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ScreenCommunityDetails(
    modifier: Modifier = Modifier,
    communityId: String,
    communityDetailsViewModelOld: CommunityDetailsViewModelOld = koinViewModel(parameters = {
        parametersOf(
            communityId
        )
    }),
    meetingsViewModel: MeetingsViewModelOld = koinViewModel(),
    navController: NavHostController,
    onBackPressed: () -> Unit
) {
    val communityDetails by communityDetailsViewModelOld.getCommunityDetailsFlow()
        .collectAsStateWithLifecycle()
    val meetingsList by meetingsViewModel.getMeetingsListFlow().collectAsStateWithLifecycle()
    val expanded by communityDetailsViewModelOld.isExpandedFlow().collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TobBarAdditionalScreens("${communityDetails?.name}", navController, onBackPressed)
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
                ExpandableText(
                    modifier = Modifier,
                    text = stringResource(id = R.string.lorem_ipsum),
                    maxLines = Int.MAX_VALUE,
                    maxLinesMinimise = 8,
                    expanded = expanded
                ) {
                    communityDetailsViewModelOld.toggleExpanded()
                    Log.d("TAG", "$expanded")
                }
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