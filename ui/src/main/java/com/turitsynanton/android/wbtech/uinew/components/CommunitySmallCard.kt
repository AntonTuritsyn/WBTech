package com.turitsynanton.android.wbtech.uinew.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay
import com.turitsynanton.android.wbtech.uinew.items.ImageHolder
import com.turitsynanton.android.wbtech.uinew.items.SimpleTextField
import com.turitsynanton.android.wbtech.uinew.items.SubscribeButton
import com.turitsynanton.android.wbtech.uinew.utils.EventCardStyles
import com.turitsynanton.android.wbtech.uinew.utils.SubscribeButtonStyle

@Composable
internal fun CommunitySmallCard(
    modifier: Modifier,
    image: String,
    communityName: String,
    subscribeButtonStyle: SubscribeButtonStyle,
    onButtonClick: () -> Unit,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .width(104.dp)
    ) {
        Column(
            modifier = Modifier
                .clickable { onClick() }
        ) {
            ImageHolder(modifier = Modifier, image = image, height = EventCardStyles.Small)
            Spacer(modifier = Modifier.padding(2.dp))
            SimpleTextField(
                modifier = Modifier,
                text = communityName,
                fontFamily = SfProDisplay,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Normal,
                color = Color(0xFF000000),
                maxLines = 1
            )
        }
        Spacer(modifier = Modifier.padding(2.dp))
        SubscribeButton(
            modifier = Modifier
                .fillMaxWidth(),
            style = subscribeButtonStyle
        ) {
            onButtonClick()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CommunityCardPreview() {
    CommunitySmallCard(
        modifier = Modifier,
        image = "",
        communityName = "Супер тестировщики",
        subscribeButtonStyle = SubscribeButtonStyle.Default,
        onButtonClick = {}
    ) {}
}