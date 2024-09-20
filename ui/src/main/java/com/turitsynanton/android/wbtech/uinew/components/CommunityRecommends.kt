package com.turitsynanton.android.wbtech.uinew.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.wbtech.models.UiCommunityCard
import com.turitsynanton.android.wbtech.uinew.items.SimpleTextField
import com.turitsynanton.android.wbtech.uinew.utils.SubscribeButtonStyle

@Composable
internal fun CommunityRecommends(
    modifier: Modifier,
    recommendationName: String,
    communitiesList: List<UiCommunityCard>,
    subscribeButtonStyle: SubscribeButtonStyle,
    onSubscribeButtonClick: (String) -> Unit,
    onElementClick: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        SimpleTextField(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            text = recommendationName,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF000000),
            maxLines = 2,
            lineHeight = 26.sp
        )
        Spacer(modifier = Modifier.padding(8.dp))
        LazyRow(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(communitiesList.size) { index ->
                CommunitySmallCard(
                    modifier = Modifier
                        .then(
                            when (index) {
                                0 -> Modifier.padding(start = 16.dp)
                                communitiesList.size - 1 -> Modifier.padding(end = 16.dp)
                                else -> Modifier
                            }
                        ),
                    image = communitiesList[index].image,
                    communityName = communitiesList[index].name,
                    subscribeButtonStyle = subscribeButtonStyle,
                    onButtonClick = { onSubscribeButtonClick(communitiesList[index].id) }
                ) {
                    onElementClick(communitiesList[index].id)
                }
            }
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
private fun CommunityRecommendsPreview() {
    CommunityRecommends(
        modifier = Modifier,
        recommendationName = "Сообщества для тестировщиков",
        communitiesList = */
/*generateCommunitiesList()*//*
listOf(),
        subscribeButtonStyle = SubscribeButtonStyle.Default,
        onButtonClick = {}
    ) {}
}*/
