package com.turitsynanton.android.wbtech.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.wbtech.R
import com.turitsynanton.android.wbtech.ui.items.Avatar
import com.turitsynanton.android.wbtech.ui.items.MyFilterChip
import com.turitsynanton.android.wbtech.ui.items.SomeText
import com.turitsynanton.android.wbtech.data.MeetingTag
import com.turitsynanton.android.wbtech.data.meetingTag
import com.turitsynanton.android.wbtech.ui.theme.NeutralWeak
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay

@Composable
fun MeetingCard(
    modifier: Modifier,
    resId: Int,
    meetingName: String,
    ended: Boolean,
    meetingDate: String,
    meetingCity: String,
    meetingTags: List<MeetingTag>
) {
    val isEnded by remember {
        mutableStateOf(ended)
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Avatar(
            modifier = Modifier
                .padding(4.dp)
                .size(48.dp),
            resId = resId,
        )
        Column(
            modifier = Modifier
                .padding(start = 16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Row {
                SomeText(
                    modifier = Modifier,
                    text = meetingName,
                    fontFamily = SfProDisplay,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Normal,
                    color = Color.Unspecified
                )
                Spacer(modifier = Modifier.weight(1f))
                if (isEnded) {
                    SomeText(
                        modifier = Modifier,
                        text = "Закончилась",
                        fontFamily = SfProDisplay,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal,
                        color = NeutralWeak
                    )
                }
            }
            Row {
                SomeText(
                    modifier = Modifier,
                    text = "$meetingDate - $meetingCity",
                    fontFamily = SfProDisplay,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Normal,
                    color = NeutralWeak
                )
            }
            LazyRow(
                modifier = Modifier
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(meetingTags.size) { index ->
                    MyFilterChip(
                        modifier = Modifier,
                        text = meetingTags[index].name
                    )
                }
            }
        }
    }
}