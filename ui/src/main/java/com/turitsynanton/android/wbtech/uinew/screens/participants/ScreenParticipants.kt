package com.turitsynanton.android.wbtech.uinew.screens.participants

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.turitsynanton.android.wbtech.uinew.components.Person
import com.turitsynanton.android.wbtech.uinew.components.TopBar
import com.turitsynanton.android.wbtech.uinew.utils.TopBarStyles
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun ScreenParticipants(
    modifier: Modifier = Modifier,
    eventId: String,
    participantsViewModel: ScreenParticipantsViewModel = koinViewModel(parameters = {
        parametersOf(
            eventId
        )
    }),
    onBackClick: () -> Unit,
    onUserClick: (String) -> Unit
) {
    val user by participantsViewModel.getParticipantsListFlow().collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            TopBar(
                modifier = Modifier,
                title = "Пойдут на встречу",
                topBarStyle = TopBarStyles.Empty,
                onIconClick = {}) {
                onBackClick()
            }
        }
    ) {
        LazyVerticalStaggeredGrid(
            modifier = modifier
                .fillMaxSize()
                .padding(it),
            columns = StaggeredGridCells.Fixed(3),
            verticalItemSpacing = 24.dp,
            horizontalArrangement = Arrangement.Center,
        ) {
            items(user.size) { index ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
//                            TODO исправить. Выглядит красиво, но постоянно перерисовывается
                            top = if (index < 3) 32.dp else 0.dp,
                            bottom = if (index >= user.size - 3) 32.dp else 0.dp
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Person(
                        modifier = Modifier,
                        user = user[index]
                    ) {
                        onUserClick(user[index].id)
                    }
                }
            }
        }
    }
}