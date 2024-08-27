package com.turitsynanton.android.wbtech.uinew.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.ui.theme.NeutralActive
import com.turitsynanton.android.wbtech.uinew.items.SimpleTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopBar(
    modifier: Modifier,
    title: String,
    needActions: Boolean,
    onShareClick: () -> Unit,
    onBackPressed: () -> Unit
) {

    CenterAlignedTopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Unspecified,
            titleContentColor = NeutralActive
        ),
        title = {
            SimpleTextField(
                modifier = Modifier,
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF000000),
                maxLines = 1
            )
        },
        navigationIcon = {
            IconButton(onClick = { onBackPressed() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Sharp.KeyboardArrowLeft,
                    contentDescription = "back",
                    tint = Color(0xFF9A10F0)
                )
            }
        },
        actions = {
            if (needActions) {
                IconButton(onClick = { onShareClick() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_share),
                        contentDescription = "back",
                        tint = Color(0xFF9A10F0)
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun TopBarPreview() {
    TopBar(modifier = Modifier, title = "Пойдут на встречу", needActions = true, onShareClick = {}) {
    }
}