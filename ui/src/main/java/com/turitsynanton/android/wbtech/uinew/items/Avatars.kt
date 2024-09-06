package com.turitsynanton.android.wbtech.uinew.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.models.UiPersonCard
import com.turitsynanton.android.wbtech.uinew.utils.AvatarStyles

@Composable
internal fun Avatar(
    modifier: Modifier,
    avatarStyle: AvatarStyles,
    user: UiPersonCard
) {
    Image(
        painter = painterResource(id = R.drawable.my_photo),
        contentDescription = null,
        modifier = modifier
            .clip(shape = CircleShape)
            .size(avatarStyle.size),
        contentScale = ContentScale.Crop
    )
}

/*
@Preview(showBackground = true)
@Composable
private fun AvatarPreview() {
    Avatar(modifier = Modifier, avatarStyle = AvatarStyles.Medium, )
}*/
