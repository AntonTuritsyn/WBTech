package com.turitsynanton.android.wbtech.ui.screens.additionalscreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.turitsynanton.android.wbtech.R
import com.turitsynanton.android.wbtech.data.storage.models.MeetingTag
import com.turitsynanton.android.wbtech.navigation.topbars.TobBarAdditionalScreens
import com.turitsynanton.android.wbtech.ui.components.People
import com.turitsynanton.android.wbtech.ui.items.MyFilledButton
import com.turitsynanton.android.wbtech.ui.items.MyFilterChip
import com.turitsynanton.android.wbtech.ui.items.SomeText
import com.turitsynanton.android.wbtech.ui.organisms.MapView
import com.turitsynanton.android.wbtech.ui.theme.BrandColorDefault
import com.turitsynanton.android.wbtech.ui.theme.NeutralWeak
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenMeetingDetails(
    modifier: Modifier,
    meetingTags: List<MeetingTag>,
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            TobBarAdditionalScreens("Developer meeting", navController, onBackPressed = {})
        }
    ) {
        Column(
            modifier = modifier
                .padding(it)
                .padding(top = 16.dp)
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
        ) {
            SomeText(
                modifier = Modifier
                    .padding(vertical = 4.dp),
                text = "13.09.2024 — Москва, ул. Громова, 4",
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
            SomeText(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .height(170.dp),
                text = stringResource(id = R.string.loremIpsum),
                fontFamily = SfProDisplay,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Normal,
                color = NeutralWeak
            )
            Spacer(modifier = Modifier.weight(1f))
            People(modifier = Modifier, size = 100)
            MyFilledButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 20.dp),
                text = "Пойду на встречу!",
                color = BrandColorDefault, onClick = {}
            )
        }
    }
}