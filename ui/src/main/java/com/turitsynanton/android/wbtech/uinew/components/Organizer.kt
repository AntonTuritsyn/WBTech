package com.turitsynanton.android.wbtech.uinew.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.data.mocks.generateCommunity
import com.turitsynanton.android.wbtech.data.storage.newmodels.DataCommunity
import com.turitsynanton.android.wbtech.uinew.items.SimpleTextField
import com.turitsynanton.android.wbtech.uinew.items.SubscribeButton
import com.turitsynanton.android.wbtech.uinew.utils.SubscribeButtonStyle

@Composable
internal fun Organizer(
    modifier: Modifier,
    community: DataCommunity,
    onButtonClick: () -> Unit,
    onElementClick: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        SimpleTextField(
            modifier = Modifier,
            text = stringResource(R.string.organizer),
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF000000)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onElementClick() },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 10.dp)
            ) {
                SimpleTextField(
                    modifier = Modifier,
                    text = community.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF000000)
                )
                SimpleTextField(
                    modifier = Modifier,
                    text = community.description,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF000000),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 5
                )
            }
            Box(
                modifier = Modifier
                    .size(104.dp)
                    .clip(shape = RoundedCornerShape(16.dp))
            ) {
                Image(
                    modifier = Modifier
                        .size(104.dp)
                        .clip(shape = RoundedCornerShape(16.dp)),
                    painter = painterResource(id = R.drawable.my_photo),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                SubscribeButton(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(bottom = 8.dp, start = 4.dp),
                    style = SubscribeButtonStyle.Default
                ) {
                    onButtonClick()
                }
            }
        }
    }
}

@Preview(
    widthDp = 360,
    showBackground = true
)
@Composable
private fun OrganizerPreview() {
    Organizer(modifier = Modifier, community = generateCommunity(), onButtonClick = {}) {

    }
}