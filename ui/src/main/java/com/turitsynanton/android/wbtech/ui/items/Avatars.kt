package com.turitsynanton.android.wbtech.ui.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.models.UiPersonCard
import com.turitsynanton.android.wbtech.ui.utils.AvatarStyles

@Composable
internal fun Avatar(
    modifier: Modifier,
    avatarStyle: AvatarStyles,
    user: UiPersonCard
) {
//    TODO изменить после получения бэка
    if (user.avatar == "profile") {
        Image(
            painter = painterResource(id = R.drawable.my_photo),
            contentDescription = null,
            modifier = modifier
                .clip(shape = CircleShape)
                .size(avatarStyle.size),
            contentScale = ContentScale.Crop
        )
    } else {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(user.avatar)
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = modifier
                .size(avatarStyle.size)
                .clip(shape = CircleShape),
            contentScale = ContentScale.Crop
        )
    }
}
