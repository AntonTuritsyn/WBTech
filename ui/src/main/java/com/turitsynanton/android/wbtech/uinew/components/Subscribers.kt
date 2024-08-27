package com.turitsynanton.android.wbtech.uinew.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay
import com.turitsynanton.android.wbtech.uinew.items.Avatar
import com.turitsynanton.android.wbtech.uinew.items.MorePeople
import com.turitsynanton.android.wbtech.uinew.items.SimpleTextField
import com.turitsynanton.android.wbtech.uinew.utils.AvatarStyles

@Composable
internal fun Subscribers(
    modifier: Modifier,
    title: String,
    avatarsList: List<Int>,
    onClick: () -> Unit
) {
//    необходимо изменить на значение ширины Column, а не экрана
//    сократить до 3-5 элементов
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    val avatarSize = 48.dp
    val spacing = 10.dp
    val maxAvatars = ((screenWidth - avatarSize) / (avatarSize - spacing)).toInt()

    Column(
        modifier = Modifier
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
            horizontalArrangement = Arrangement.spacedBy(-spacing),
            reverseLayout = false,
            modifier = Modifier
                .fillMaxWidth(),
            userScrollEnabled = false
        ) {
            val avatarsToShow = if (avatarsList.size > maxAvatars) maxAvatars else avatarsList.size
            items(5) { index ->
                Avatar(
                    modifier = Modifier,
                    avatarStyle = AvatarStyles.Small,
                    res = avatarsList[index]
                )
            }
            item {

//            изменить numberOfPeople
                MorePeople(modifier = Modifier, numberOfPeople = avatarsList.size)
            }
        }
    }

}

@Preview(
    /*widthDp = 400,
    heightDp = 200,
    backgroundColor = 0xFF00FF00,*/
    showBackground = true,
)
@Composable
private fun SubscriberPreview() {
    Subscribers(
        modifier = Modifier,
        title = stringResource(id = R.string.subscribers),
        avatarsList = avatars
    ) {}
}

val avatars = List(30) {
    R.drawable.my_photo
}
