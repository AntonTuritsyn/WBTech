package com.turitsynanton.android.wbtech.uinew.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.data.mocks.generateCommunity
import com.turitsynanton.android.wbtech.data.mocks.generateTags
import com.turitsynanton.android.wbtech.data.storage.newmodels.DataCommunity
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
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.event_example),
            contentDescription = "",
            modifier = modifier
                .size(168.dp)
                .clip(shape = RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )
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
            modifier = Modifier
                .heightIn(max = 50.dp),
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

/*
@RequiresApi(Build.VERSION_CODES.O)
@Preview(widthDp = 360,
    showBackground = true)
@Composable
private fun CommunityLargeCardPreview() {
    CommunityLargeCard(
        modifier = Modifier,
        community = generateCommunity()
    )
}*/
