package com.turitsynanton.android.wbtech.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.turitsynanton.android.wbtech.R
import com.turitsynanton.android.wbtech.data.Community
import com.turitsynanton.android.wbtech.data.Meeting
import com.turitsynanton.android.wbtech.navigation.TobBarAdditionalScreens
import com.turitsynanton.android.wbtech.ui.components.MeetingCard
import com.turitsynanton.android.wbtech.ui.components.СommunityCard
import com.turitsynanton.android.wbtech.ui.items.SomeText
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenCommunityDetails(meetingsList: List<Meeting>, modifier: Modifier, onBackPressed: () -> Unit) {
    Scaffold(
        topBar = {
            TobBarAdditionalScreens("Designa", onBackPressed)
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
                    color = Color(0xFFA4A4A4)
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
                    color = Color(0xFFA4A4A4)
                )
            }
            items(meetingsList.size) { index ->
                MeetingCard(
                    modifier = Modifier,
                    resId = R.drawable.ic_meeting,
                    meetingName = meetingsList[index].name,
                    ended = meetingsList[index].ended,
                    meetingDate = meetingsList[index].date,
                    meetingCity = meetingsList[index].city,
                    meetingTags = meetingsList[index].tags
                )
                HorizontalDivider(
                    modifier = Modifier.padding(top = 12.dp),
                    thickness = 1.dp,
                    color = Color(0xFFEDEDED)
                )
            }
        }
    }
}