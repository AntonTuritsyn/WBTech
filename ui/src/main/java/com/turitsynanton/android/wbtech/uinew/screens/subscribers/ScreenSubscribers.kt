package com.turitsynanton.android.wbtech.uinew.screens.subscribers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.turitsynanton.android.wbtech.uinew.components.Person
import com.turitsynanton.android.wbtech.uinew.components.TopBar
import com.turitsynanton.android.wbtech.uinew.utils.TopBarStyles
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun ScreenSubscribers(
    modifier: Modifier = Modifier,
    communityId: String,
    subscribersViewModel: ScreenSubscribersViewModel = koinViewModel(parameters = {
        parametersOf(
            communityId
        )
    }),
    onBackClick: () -> Unit,
    onUserClick: (String) -> Unit
) {
    val user by subscribersViewModel.getSubscribersListFlow().collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            TopBar(
                modifier = Modifier,
                title = "Подписаны",
                topBarStyle = TopBarStyles.Empty,
                onIconClick = {}
            ) {
                onBackClick()
            }
        }
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .padding(it)
                .padding(top = 32.dp, bottom = 32.dp, start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(user.chunked(3)) { rowItems ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween
                ) {
                    for (participant in rowItems) {
                        Person(
                            modifier = Modifier,
                            user = participant
                        ) {
                            onUserClick(participant.id)
                        }
                    }
                }
            }
        }
    }
}