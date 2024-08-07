package com.turitsynanton.android.wbtech.ui.screens.additionalscreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.domain.models.MeetingTag
import com.turitsynanton.android.wbtech.ui.components.ExpandableText
import com.turitsynanton.android.wbtech.ui.components.People
import com.turitsynanton.android.wbtech.ui.items.MyFilledButton
import com.turitsynanton.android.wbtech.ui.items.MyFilterChip
import com.turitsynanton.android.wbtech.ui.items.SomeText
import com.turitsynanton.android.wbtech.ui.organisms.MapView
import com.turitsynanton.android.wbtech.ui.screens.viewmodels.MeetingDetailsViewModel
import com.turitsynanton.android.wbtech.ui.theme.BrandColorDefault
import com.turitsynanton.android.wbtech.ui.theme.NeutralWeak
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay
import com.turitsynanton.android.wbtech.ui.topbars.TobBarAdditionalScreens
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ScreenMeetingDetails(
    modifier: Modifier = Modifier,
    meetingTags: List<MeetingTag>,
    meetingId: String,
    meetingDetailsViewModel: MeetingDetailsViewModel = koinViewModel(parameters = {
        parametersOf(
            meetingId
        )
    }),
    navController: NavHostController,
    onBackPressed: () -> Unit
) {
    val meetingDetails by meetingDetailsViewModel.getMeetingDetailsFlow()
        .collectAsStateWithLifecycle()
    val expanded by meetingDetailsViewModel.isExpandedFlow().collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TobBarAdditionalScreens("${meetingDetails?.name}", navController, onBackPressed)
        }
    ) {
        Column(
            modifier = modifier
                .padding(it)
                .padding(top = 16.dp)
                .padding(horizontal = 24.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            SomeText(
                modifier = Modifier
                    .padding(vertical = 4.dp),
                text = "${meetingDetails?.date} - ${meetingDetails?.city}",
                fontFamily = SfProDisplay,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Normal,
                color = NeutralWeak
            )
            LazyRow(
                modifier = Modifier.padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(meetingTags.size) { index ->
                    MyFilterChip(
                        modifier = Modifier,
                        text = meetingTags[index].name
                    )
                }
            }
            Spacer(modifier = Modifier.padding(bottom = 16.dp))
            MapView()
            Spacer(modifier = Modifier.padding(bottom = 20.dp))
            ExpandableText(
                modifier = Modifier,
                text = stringResource(id = R.string.loremIpsum),
                maxLines = Int.MAX_VALUE,
                maxLinesMinimise = 8,
                expanded = expanded
            ) {
                meetingDetailsViewModel.toggleExpanded()
            }
            Spacer(modifier = Modifier.weight(1f))
            People(modifier = Modifier, size = 100)
            MyFilledButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 20.dp),
                text = "Пойду на встречу!",
                color = BrandColorDefault,
                onClick = {}
            )
        }
    }
}