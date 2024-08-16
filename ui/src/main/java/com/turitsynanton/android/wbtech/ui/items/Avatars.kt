package com.turitsynanton.android.wbtech.ui.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.ui.theme.NeutralSecondaryBG

@Composable
internal fun Avatar(modifier: Modifier, resId: Int) {
    Image(
        modifier = modifier,
        painter = painterResource(id = resId),
        contentDescription = "common",
        contentScale = ContentScale.Crop
    )
}
@Composable
internal fun RandomAvatar(modifier: Modifier, resId: Int) {
    AsyncImage(model = "https://api.api-ninjas.com/v1/randomimage", contentDescription = "", contentScale = ContentScale.Crop)
    /*Image(
        modifier = modifier,
        painter = painterResource(id = resId),
        contentDescription = "common",
        contentScale = ContentScale.Crop
    )*/
}

@Composable
internal fun Avatars(modifier: Modifier, resId: Int) {
    Avatar(
        modifier = Modifier
            .padding(2.dp),
        resId = resId
    )
}

@Preview(showBackground = true)
@Composable
internal fun Show() {
    CustomAvatar(modifier = Modifier, variant = 2, resId = R.drawable.icon_variant_user)
//    Avatar(modifier = Modifier, resId = R.drawable.my_photo)
}

@Composable
internal fun CustomAvatar(
    modifier: Modifier, variant: Int, resId: Int
) {
    val size = remember(variant) {
        when (variant) {
            1 -> 50.dp
            2 -> 100.dp
            3 -> 200.dp
            else -> 200.dp
        }
    }
    val scale = remember(variant) {
        when (variant) {
            1 -> 0.5f
            2 -> 1.0f
            3 -> 2.0f
            else -> 1.0f
        }
    }
    Box(
        modifier
            .size(
                size
            )
            .background(
                color = NeutralSecondaryBG,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Avatar(
            modifier = Modifier
                .scale(
                    if (resId == R.drawable.icon_variant_user) {
                        scale
                    } else {
                        1f
                    }
                )
                .clip(
                    (if (resId != R.drawable.icon_variant_user) {
                        CircleShape
                    } else {
                        RectangleShape
                    })
                ), resId = resId
        )
        if (variant == 2) {
            Icon(
                imageVector = Icons.Sharp.AddCircle,
                contentDescription = "add",
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
    }
}