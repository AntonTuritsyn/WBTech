package com.turitsynanton.android.wbtech.ui.components

import androidx.compose.foundation.Image
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
import com.turitsynanton.android.wbtech.ui.theme.NeutralActive
import com.turitsynanton.android.wbtech.ui.items.SimpleTextField
import com.turitsynanton.android.wbtech.ui.utils.TopBarStyles

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopBar(
    modifier: Modifier,
    title: String,
    topBarColor: Color = Color.Unspecified,
    topBarStyle: TopBarStyles,
    onIconClick: () -> Unit,
    onBackPressed: () -> Unit
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = topBarColor,
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
                Image(
                    painter = painterResource(topBarStyle.backIcon),
                    contentDescription = "back",
                )
            }
        },
        actions = {
            when (topBarStyle) {
                TopBarStyles.Empty -> {}
                TopBarStyles.Save -> {
                    IconButton(onClick = { onIconClick() }) {
                        Icon(
                            painter = painterResource(id = topBarStyle.icon),
                            contentDescription = "back",
                            tint = Color(0xFF9A10F0)
                        )
                    }
                }
                TopBarStyles.Edit -> {
                    IconButton(onClick = { onIconClick() }) {
                        Icon(
                            painter = painterResource(id = topBarStyle.icon),
                            contentDescription = "back",
                            tint = Color(0xFF9A10F0)
                        )
                    }
                }
                TopBarStyles.Share -> {
                    IconButton(onClick = { onIconClick() }) {
                        Icon(
                            painter = painterResource(id = topBarStyle.icon),
                            contentDescription = "back",
                            tint = Color(0xFF9A10F0)
                        )
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun TopBarPreview() {
    TopBar(modifier = Modifier, title = "Пойдут на встречу", topBarStyle = TopBarStyles.Edit, onIconClick = {}) {
    }
}