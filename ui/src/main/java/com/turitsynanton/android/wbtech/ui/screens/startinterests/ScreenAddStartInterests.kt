package com.turitsynanton.android.wbtech.ui.screens.startinterests

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.data.mocks.tags
import com.turitsynanton.android.wbtech.ui.components.OtherEvents
import com.turitsynanton.android.wbtech.ui.items.GradientButton
import com.turitsynanton.android.wbtech.ui.items.SimpleTextField
import com.turitsynanton.android.wbtech.ui.utils.ButtonStyle
import com.turitsynanton.android.wbtech.ui.utils.TagsStyle

@Composable
internal fun ScreenAddStartInterests(
    modifier: Modifier = Modifier,
    onTagClick: () -> Unit,
    onSaveClick: () -> Unit,
    onTellLaterClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        SimpleTextField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 12.dp),
            text = stringResource(id = R.string.interests),
            fontSize = 48.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF000000)
        )
        SimpleTextField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 24.dp),
            text = stringResource(id = R.string.choose_interests),
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 22.sp,
            color = Color(0xFF000000)
        )
        OtherEvents(
            modifier = Modifier,
            title = 0,
            tagsList = tags.map { tag ->
                tag to if (/*screenEventsListViewModel.isTagSelected(tag)*/true) {
                    TagsStyle.SelectedBig
                } else {
                    TagsStyle.UnselectedBig
                }
            }
        ) { tag ->
            /*screenEventsListViewModel.onTagSelected(tag)*/
        }
        Spacer(modifier = Modifier.weight(1f))
        GradientButton(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            text = stringResource(id = R.string.save),
            buttonStyle = ButtonStyle.Enable,
            onClick = { onSaveClick() }
        )
        SimpleTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp)
                .clickable {
                    onTellLaterClick()
                },
            text = stringResource(R.string.tell_later_button),
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF76778E),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    ScreenAddStartInterests(
        modifier = Modifier,
        onTagClick = {},
        onSaveClick = {},
        onTellLaterClick = {}
    )
}