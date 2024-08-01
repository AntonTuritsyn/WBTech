package com.turitsynanton.android.wbtech.ui.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.wbtech.ui.theme.NeutralDisabled
import com.turitsynanton.android.wbtech.ui.theme.NeutralSecondaryBG
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SearchField(modifier: Modifier, isEnabled: Boolean = true) {
    var query: String by rememberSaveable { mutableStateOf("") }
    Row(
        Modifier
            .padding(vertical = 16.dp)
            .height(36.dp)
            .fillMaxWidth()
            .background(shape = RoundedCornerShape(4.dp), color = NeutralSecondaryBG)
            .clickable { },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Sharp.Search,
            modifier = Modifier
                .padding(start = 8.dp),
            contentDescription = "",
            tint = NeutralDisabled
        )
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = query,
            onValueChange = {
                query = it
            },
            enabled = isEnabled,
            textStyle = TextStyle(
                color = Color(0xFF666666),
                fontSize = 14.sp,
                fontFamily = SfProDisplay,
                fontWeight = FontWeight.Normal,
            ),
            decorationBox = { innerTextField ->
                if (query.isEmpty()) {
                    Text(
                        text = "Поиск",
                        color = NeutralDisabled,
                        fontSize = 14.sp,
                        fontFamily = SfProDisplay,
                        fontWeight = FontWeight.Normal
                    )
                }
                innerTextField()
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
internal fun SearchFieldPreview() {
    SearchField(modifier = Modifier)
}