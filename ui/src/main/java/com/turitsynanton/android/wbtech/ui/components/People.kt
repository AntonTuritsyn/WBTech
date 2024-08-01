package com.turitsynanton.android.wbtech.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.ui.items.Avatar

internal fun <T> MutableList<T>.randomSubset(maxSize: Int): List<T> {
    if (this.isEmpty()) return emptyList()
    val resultSize = minOf(this.size, maxSize)
    return this.shuffled().take(resultSize)
}

@Composable
internal fun People(modifier: Modifier, size: Int) {

    val avatars = mutableListOf<Int>()
    for (i in 1..size) {
        avatars.add(R.drawable.ic_meeting)
    }

    var newList = avatars.randomSubset(5)

    Box(
        modifier = Modifier
            .width(220.dp)
            .padding(horizontal = 4.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(
                when (size) {
                    in 1..3 -> 8.dp
                    4 -> -8.dp
                    else -> -16.dp
                }
            ),
            reverseLayout = true,
            modifier = Modifier
                .width(186.dp),
            userScrollEnabled = false
        ) {
            items(newList.size) { index ->
                Card(
                    modifier = Modifier
                        .size(48.dp)
                        .border(width = 2.dp, Color(0xFFD2D5F9), shape = RoundedCornerShape(16.dp)),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardColors(
                        containerColor = Color(0xFFD2D5F9),
                        contentColor = Color.Unspecified,
                        disabledContentColor = Color.Unspecified,
                        disabledContainerColor = Color.Unspecified
                    )
                ) {
                    Avatar(modifier = Modifier
                        .fillMaxSize(), resId = avatars[index])
                }
            }
        }
        Text(
            modifier = Modifier
                .align(Alignment.CenterEnd),
            text = if (size > 5) "+${size - 5}" else "",
            style = TextStyle(
                fontSize = 14.sp
            )
        )
    }
}