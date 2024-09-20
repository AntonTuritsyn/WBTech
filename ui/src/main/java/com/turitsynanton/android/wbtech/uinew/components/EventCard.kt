package com.turitsynanton.android.wbtech.uinew.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.models.UiTag
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay
import com.turitsynanton.android.wbtech.uinew.items.ImageHolder
import com.turitsynanton.android.wbtech.uinew.items.SimpleTextField
import com.turitsynanton.android.wbtech.uinew.items.Tag
import com.turitsynanton.android.wbtech.uinew.utils.EventCardStyles
import com.turitsynanton.android.wbtech.uinew.utils.TagsStyle

@Composable
internal fun EventCard(
    modifier: Modifier,
    eventId: String,
    eventName: String,
    eventDate: String,
    eventAddress: String,
    eventTags: List<UiTag>,
    eventImage: String,
    eventStyle: EventCardStyles,
    onClick: (String) -> Unit
) {
    Column(
        modifier = modifier
            .then(
                if (eventStyle.name == "Full") {
                    Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                } else {
                    Modifier
                        .width(eventStyle.width)
                }
            )
            .clip(
                RoundedCornerShape(
                    topEnd = 16.dp,
                    topStart = 16.dp,
                    bottomEnd = 8.dp,
                    bottomStart = 8.dp
                )
            )
            .clickable { onClick(eventId) }
    ) {
        ImageHolder(
            modifier = Modifier,
            image = eventImage,
            height = eventStyle
        )
        Spacer(modifier = Modifier.padding(4.dp))
        SimpleTextField(
            modifier = Modifier,
            text = eventName,
            fontFamily = SfProDisplay,
            fontSize = eventStyle.textHeight,
            fontWeight = eventStyle.fontWeight,
            fontStyle = FontStyle.Normal,
            color = Color(0xFF000000),
            lineHeight = eventStyle.lineHeight,
            maxLines = if (eventStyle.name == "Full") 2 else 1
        )
        SimpleTextField(
            modifier = Modifier,
            text = "$eventDate · $eventAddress",
            fontFamily = SfProDisplay,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Normal,
            color = Color(0xFF76778E),
            maxLines = 1
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            eventTags.forEach { content ->
                Tag(modifier = Modifier, text = content.content, style = TagsStyle.Minimize) {
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EventCardPreview() {
    EventCard(
        modifier = Modifier,
        eventStyle = EventCardStyles.Full,
        eventId = "",
        eventName = "Python daysPython daysPython daysPython daysPython days",
        eventDate = "10 августа",
        eventAddress = "Кожевенная линия, 40",
        eventImage = "painterResource(id = R.drawable.event_example)",
        eventTags = listOf()
    ) {

    }
}