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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.wbtech.models.UiTag
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay
import com.turitsynanton.android.wbtech.uinew.items.ImageHolder
import com.turitsynanton.android.wbtech.uinew.items.SimpleTextField
import com.turitsynanton.android.wbtech.uinew.items.SpecialOtherTag
import com.turitsynanton.android.wbtech.uinew.items.Tag
import com.turitsynanton.android.wbtech.uinew.utils.EventCardStyles
import com.turitsynanton.android.wbtech.uinew.utils.TagsStyle

private const val TAG = "EventCard"
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
//    var tagWidth by remember { mutableIntStateOf(0) }
    var specialTagWidth by remember { mutableIntStateOf(0) }
    var columnWidth by remember { mutableIntStateOf(0) }
    var rowWidth by remember { mutableIntStateOf(0) }
    var totalTagWidth by remember { mutableIntStateOf(0) }
    val density = LocalDensity.current
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
            .onGloballyPositioned { layoutCoordinates ->
                val widthInPx = layoutCoordinates.size.width
//                        val density = LocalDensity.current
                columnWidth = /*with(density) { widthInPx.toDp() }*/widthInPx
            }
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
            modifier = Modifier
                .onGloballyPositioned { layoutCoordinates ->
                    val widthInPx = layoutCoordinates.size.width
                    rowWidth = /*with(density) { widthInPx.toDp() }*/widthInPx
                },
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            totalTagWidth = 0
//            Log.d(TAG, "totalTagWidth: $totalTagWidth")
            eventTags.sortedBy { it.content.length }.forEach { content ->
                var tagWidth by remember { mutableIntStateOf(0) }
                val spacingInPx = with(density) { 6.dp.toPx() }
                totalTagWidth += (tagWidth + spacingInPx.toInt())
                val experiment = columnWidth - specialTagWidth < totalTagWidth
//                rowWidth += tagWidth
//                Log.d(TAG, "rowWidth2: $rowWidth")
                Tag(
                    modifier = Modifier
                        .onGloballyPositioned { coordinates ->
                            tagWidth = coordinates.size.width
//                            Log.d(TAG, "tagWidth: $tagWidth")
//                            Log.d(TAG, "rowWidth: $rowWidth")
//                            Log.d(TAG, "columnWidth: $columnWidth")
//                            Log.d(TAG, "totalTagWidth: $totalTagWidth ${eventName}")

                        }
                        /*.alpha(0f)*/,
                    text = content.content,
                    style = TagsStyle.Minimize
                ) {
                }
            }
            SpecialOtherTag(
                modifier = Modifier/*.alpha(0f)*/
                    .onGloballyPositioned { layoutCoordinates ->
                        val widthInPx = layoutCoordinates.size.width
                        specialTagWidth = /*with(density) { widthInPx.toDp() }*/widthInPx
                    },
                text = "+Ещё",
                style = TagsStyle.Minimize
            )
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