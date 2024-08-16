package com.turitsynanton.android.wbtech.uinew.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay
import com.turitsynanton.android.wbtech.uinew.utils.TagsStyle

@Composable
internal fun Tag(
    modifier: Modifier,
    text: String,
    style: TagsStyle,
    onClick: () -> Unit
) {
    SimpleTextField(
        modifier = modifier
            .clickable(
                indication = null,
                interactionSource = remember {
                    MutableInteractionSource()
                },
                enabled = style.clickable
            ) {
                onClick()
            }
            .background(
                color = style.containerColor,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(vertical = style.verticalPaddings, horizontal = style.horizontalPaddings),
        text = text,
        fontFamily = SfProDisplay,
        fontSize = style.fontSize,
        fontWeight = FontWeight.Medium,
        fontStyle = FontStyle.Normal,
        color = style.textColor
    )
}

@Preview(showBackground = true)
@Composable
fun TagPreview() {
    Tag(
        modifier = Modifier,
        text = "Тестирование",
        style = TagsStyle.SelectedBig
    ) {

    }
}