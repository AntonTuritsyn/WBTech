package com.turitsynanton.android.wbtech.uinew.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.data.mocks.generateTagsForEvent
import com.turitsynanton.android.wbtech.data.mocks.tags
import com.turitsynanton.android.wbtech.data.storage.newmodels.DataTag
import com.turitsynanton.android.wbtech.uinew.items.SimpleTextField
import com.turitsynanton.android.wbtech.uinew.items.Tag
import com.turitsynanton.android.wbtech.uinew.utils.TagsStyle

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun OtherEvents(
    modifier: Modifier,
    tagsList: List<String>,
    tagsStyle: TagsStyle
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        SimpleTextField(
            modifier = Modifier,
            text = stringResource(id = R.string.other_events),
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF000000)
        )
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            tagsList.sorted().forEach { content ->
                Tag(modifier = Modifier, text = content, style = tagsStyle) {
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OtherEventsPreview() {
    OtherEvents(modifier = Modifier, tagsList = tags, tagsStyle = TagsStyle.Unselected)
}