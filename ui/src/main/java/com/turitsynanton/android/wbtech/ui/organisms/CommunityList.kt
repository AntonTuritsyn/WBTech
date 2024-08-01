package com.turitsynanton.android.wbtech.ui.organisms

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.turitsynanton.android.ui.R
import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import com.turitsynanton.android.wbtech.ui.components.СommunityCard
import com.turitsynanton.android.wbtech.ui.theme.NeutralLine

@Composable
fun CommunityList(domainCommunityList: List<DomainCommunity>, onClick: (/*Community*/) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(domainCommunityList) { item ->
            СommunityCard(
                modifier = Modifier
                    .clickable {
                        onClick(/*item*/)
                    },
                resId = R.drawable.ic_designa,
                text = item.name,
                communitySize = item.size
            )
            HorizontalDivider(
                modifier = Modifier.padding(top = 12.dp),
                thickness = 1.dp,
                color = NeutralLine
            )
        }
    }
}