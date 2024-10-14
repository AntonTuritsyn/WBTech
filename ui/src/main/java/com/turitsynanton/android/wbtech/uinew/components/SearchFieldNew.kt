package com.turitsynanton.android.wbtech.uinew.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay

@Composable
internal fun SearchFieldNew(
    modifier: Modifier,
    query: String,
    onQueryChanged: (String) -> Unit,
    onCrossClick: () -> Unit
) {
    BasicTextField(
        modifier = Modifier
            .then(modifier)
            .background(color = Color(0xFFF6F6FA), shape = RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 10.dp)
            .wrapContentHeight(Alignment.CenterVertically),
        value = query,
        onValueChange = {
            onQueryChanged(it)
        },
        enabled = true,
        maxLines = 1,
        textStyle = TextStyle(
            color = Color(0xFF000000),
            fontSize = 14.sp,
            fontFamily = SfProDisplay,
            fontWeight = FontWeight.Normal
        ),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ) {
                if (query.isEmpty()) {
                    DecorationBoxForEmptyText(modifier = Modifier)
                } else {
                    DecorationBoxForSearch(modifier = Modifier) {
                        onCrossClick()
                    }
                }
                innerTextField()
            }

        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        )
    )
}

@Composable
private fun DecorationBoxForEmptyText(
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .background(
                color = Color(0xFFF6F6FA)
            )
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            modifier = Modifier
                .padding(end = 6.dp),
            contentDescription = "",
            tint = Color.Unspecified
        )
        Text(
            text = stringResource(id = R.string.search_placeholder),
            color = Color(0xFF76778E),
            fontSize = 14.sp,
            fontFamily = SfProDisplay,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun DecorationBoxForSearch(
    modifier: Modifier,
    onCrossClick: () -> Unit
    ) {
    Row(
        modifier = modifier
            .background(
                color = Color(0xFFF6F6FA)
            )
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_cross_small),
            modifier = Modifier
                .padding(end = 6.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { onCrossClick() },
            contentDescription = "",
            tint = Color.Unspecified
        )
    }
}

@Preview(
    widthDp = 400,
//    heightDp = 200,
    showBackground = true,
    backgroundColor = 0xFF00FF00
)
@Composable
internal fun SearchFieldNewPreview() {
    SearchFieldNew(modifier = Modifier, query = "", onQueryChanged = {}) {
    }
}