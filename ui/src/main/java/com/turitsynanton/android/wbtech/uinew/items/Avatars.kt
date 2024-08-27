package com.turitsynanton.android.wbtech.uinew.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.ui.theme.NeutralSecondaryBG
import com.turitsynanton.android.wbtech.uinew.utils.AvatarStyles

@Composable
internal fun Avatar(
    modifier: Modifier,
    avatarStyle: AvatarStyles,
    res: Int
) {
    Image(
        painter = painterResource(id = res),
        contentDescription = null,
        modifier = modifier
            .clip(shape = CircleShape)
            .size(avatarStyle.size),
        contentScale = ContentScale.Crop
    )
}

@Preview(showBackground = true)
@Composable
private fun AvatarPreview() {
    Avatar(modifier = Modifier, avatarStyle = AvatarStyles.Medium, res = R.drawable.my_photo)
}