package com.turitsynanton.android.wbtech.uinew.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.turitsynanton.android.wbtech.models.UiCommunity
import com.turitsynanton.android.wbtech.uinew.items.GradientButton
import com.turitsynanton.android.wbtech.uinew.items.SimpleTextField
import com.turitsynanton.android.wbtech.uinew.items.Tag
import com.turitsynanton.android.wbtech.uinew.utils.ButtonStyle
import com.turitsynanton.android.wbtech.uinew.utils.TagsStyle

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun CommunityLargeCard(
    modifier: Modifier,
    community: UiCommunity,
    onSubscribeClick: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(community.image)
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .size(168.dp)
                .clip(shape = RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )
        /*Image(
            painter = painterResource(id = R.drawable.event_example),
            contentDescription = "",
            modifier = modifier
                .size(168.dp)
                .clip(shape = RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )*/
        Spacer(modifier = Modifier.padding(4.dp))
        SimpleTextField(
            modifier = Modifier,
            text = community.name,
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF000000),
            lineHeight = 34.sp
        )
        Spacer(modifier = Modifier.padding(4.dp))
        FlowRow(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            community.tags.forEach { content ->
                Tag(modifier = Modifier, text = content.content, style = TagsStyle.UnclickableBig) {
                }
            }
        }
//        Spacer(modifier = Modifier.padding(16.dp))
        GradientButton(
            modifier = Modifier
                .padding(top = 32.dp),
            text = "Подписаться",
            buttonStyle = ButtonStyle.Enable
        ) {
            onSubscribeClick()
        }
    }
}
