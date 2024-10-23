package com.turitsynanton.android.wbtech.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.wbtech.models.UiPersonCard
import com.turitsynanton.android.wbtech.ui.items.Avatar
import com.turitsynanton.android.wbtech.ui.items.MorePeople
import com.turitsynanton.android.wbtech.ui.items.SimpleTextField
import com.turitsynanton.android.wbtech.ui.utils.AvatarStyles

private const val AVATARS_TO_SHOW = 5
@Composable
internal fun Subscribers(
    modifier: Modifier,
    title: String,
    usersCount: Int,
    avatarsList: List<UiPersonCard>,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .clickable { onClick() },
    ) {
        SimpleTextField(
            modifier = Modifier,
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF000000)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy((-10).dp),
            reverseLayout = false,
            modifier = Modifier
                .fillMaxWidth(),
            userScrollEnabled = false
        ) {
            when (usersCount) {
                0 -> {
                    items(avatarsList.size) { index ->
                        Avatar(
                            modifier = Modifier,
                            avatarStyle = AvatarStyles.Small,
                            user = avatarsList[index],
                        )
                    }
                }
                else -> {
                    items(AVATARS_TO_SHOW) { index ->
                        Avatar(
                            modifier = Modifier,
                            avatarStyle = AvatarStyles.Small,
                            user = avatarsList[index],
                        )
                    }
                    item {
                        MorePeople(modifier = Modifier, numberOfPeople = (usersCount))
                    }
                }
            }
        }
    }
}