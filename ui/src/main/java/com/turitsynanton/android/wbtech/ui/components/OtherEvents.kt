package com.turitsynanton.android.wbtech.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.ui.items.SimpleTextField
import com.turitsynanton.android.wbtech.ui.items.Tag
import com.turitsynanton.android.wbtech.ui.utils.TagsStyle

private const val TAG = "OtherEvents"
@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun OtherEvents(
    modifier: Modifier,
    tagsList: List<Pair<String, TagsStyle>>,
//    tagsStyle: TagsStyle,
    title: Int,
    onTagClick: (String) -> Unit
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        if (title != 0) {
            SimpleTextField(
                modifier = Modifier
                    .padding(bottom = 16.dp),
                text = stringResource(id = title),
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF000000)
            )
        }
        FlowRow(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            tagsList.sortedBy { it.first }.forEach { (content, style) ->
                Tag(modifier = Modifier, text = content, style = style) {
                    onTagClick(content)
                    Log.d(TAG, "OtherEvents: $content")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OtherEventsPreview() {
    OtherEvents(modifier = Modifier, title = R.string.other_events, tagsList = listOf()) {
    }
}