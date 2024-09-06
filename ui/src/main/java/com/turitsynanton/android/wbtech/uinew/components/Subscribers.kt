package com.turitsynanton.android.wbtech.uinew.components

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
import com.turitsynanton.android.wbtech.uinew.items.Avatar
import com.turitsynanton.android.wbtech.uinew.items.MorePeople
import com.turitsynanton.android.wbtech.uinew.items.SimpleTextField
import com.turitsynanton.android.wbtech.uinew.utils.AvatarStyles

private const val AVATARS_TO_SHOW = 5
@Composable
internal fun Subscribers(
    modifier: Modifier,
    title: String,
    avatarsList: List<UiPersonCard>,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
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
            horizontalArrangement = Arrangement.spacedBy(-10.dp),
            reverseLayout = false,
            modifier = Modifier
                .fillMaxWidth(),
            userScrollEnabled = false
        ) {
            when (avatarsList.size) {
                in 0..AVATARS_TO_SHOW -> {
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
//            изменить numberOfPeople
                        MorePeople(modifier = Modifier, numberOfPeople = (avatarsList.size - AVATARS_TO_SHOW))
                    }
                }
            }

        }
    }

}

/*@Preview(
    *//*widthDp = 400,
    heightDp = 200,
    backgroundColor = 0xFF00FF00,*//*
    showBackground = true,
)
@Composable
private fun SubscriberPreview() {
    Subscribers(
        modifier = Modifier,
        title = stringResource(id = R.string.subscribers),
        avatarsList = avatars
    ) {}
}*/
