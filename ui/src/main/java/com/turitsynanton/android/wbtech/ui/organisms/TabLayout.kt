package com.turitsynanton.android.wbtech.ui.organisms

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.turitsynanton.android.wbtech.ui.items.SomeText
import com.turitsynanton.android.wbtech.ui.pages
import com.turitsynanton.android.wbtech.ui.theme.BrandColorDefault
import com.turitsynanton.android.wbtech.ui.theme.SfProDisplay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun TabLayout(tabsNames: List<String>, pagerState: PagerState) {
    val tabs = tabsNames
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pages.size,
        divider = {
        },
        indicator = { tabPositions ->
            TabRowDefaults.SecondaryIndicator(
                Modifier
                    .tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                height = 2.dp
            )
        }) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = {
                    SomeText(
                        modifier = Modifier,
                        text = title.uppercase(),
                        fontFamily = SfProDisplay,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W500,
                        fontStyle = FontStyle.Normal,
                        color = Color.Unspecified
                    )
                },
                selectedContentColor = BrandColorDefault,
                unselectedContentColor = Color(0xFF666666)
            )
        }
    }
}