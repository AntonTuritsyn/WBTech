package com.turitsynanton.android.wbtech.ui.items

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.turitsynanton.android.wbtech.ui.theme.BrandColorDefault
import com.turitsynanton.android.wbtech.ui.theme.NeutralSecondaryBG

@Composable
fun MyFilledButton(modifier: Modifier, text: String, color: Color, enable: Boolean = true, onClick: () -> Unit) {
    Button(
        modifier = modifier,
        onClick = { onClick() },
        enabled = enable,
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            disabledContainerColor = BrandColorDefault,
            disabledContentColor = NeutralSecondaryBG
        )
    ) {
        Text(text = text)
    }
}

@Composable
fun MyElevatedButton(modifier: Modifier, text: String) {
    ElevatedButton(modifier = modifier, onClick = { /*TODO*/ }) {
        Text(text = text)
    }
}

@Composable
fun MyTonalButton(modifier: Modifier, text: String, color: Color) {
    FilledTonalButton(
        modifier = modifier,
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.filledTonalButtonColors(color),
//        enabled = false
    ) {
        Text(text = text)
    }
}

@Composable
fun MyOutlinedButton(modifier: Modifier, text: String, color: Color) {
    OutlinedButton(
        onClick = { /*TODO*/ },
    ) {
        Text(text = text)
    }
}

@Composable
fun MyTextButton(modifier: Modifier, text: String, onClick: () -> Unit) {
    TextButton(modifier = modifier, onClick = { onClick() }) {
        Text(text = text)
    }
}

@Composable
fun MyIconButton(modifier: Modifier, color: Color, icon: Painter) {
    OutlinedButton(
        modifier = modifier,
        onClick = { /*TODO*/ },
        border = BorderStroke(
            width = 1.67.dp, color = color
        )
    ) {
        Icon(
            painter = icon, contentDescription = ""
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSmth() {
    MyFilledButton(
        Modifier,
        "Сохранить",
        Color(0xFF660EC8),
        onClick = {}
    )
}