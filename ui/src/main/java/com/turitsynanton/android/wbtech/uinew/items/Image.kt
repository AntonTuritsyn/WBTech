package com.turitsynanton.android.wbtech.uinew.items

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.turitsynanton.android.wbtech.uinew.utils.EventCardStyles

@Composable
internal fun ImageHolder(
    modifier: Modifier,
    image: String,
    height: EventCardStyles
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(image)
            .crossfade(true)
            .build(),
        contentDescription = null,
        modifier = modifier
            .fillMaxWidth()
            .height(height.height)
            .clip(shape = RoundedCornerShape(16.dp)),
        contentScale = ContentScale.Crop
    )
}

@Preview(showBackground = true)
@Composable
private fun ImageHolderPreview() {
    ImageHolder(
        modifier = Modifier,
        image = "painterResource(id = R.drawable.event_example)",
        height = EventCardStyles.Medium
    )
}