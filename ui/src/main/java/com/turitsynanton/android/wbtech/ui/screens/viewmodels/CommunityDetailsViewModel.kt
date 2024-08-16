package com.turitsynanton.android.wbtech.ui.screens.viewmodels

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import com.turitsynanton.android.wbtech.domain.usecases.community.IGetCommunityDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.Thread.State

internal class CommunityDetailsViewModel(
    communityId: String,
    private val iGetCommunityDetailsUseCase: IGetCommunityDetailsUseCase
): ViewModel() {

    private val _community: MutableStateFlow<DomainCommunity?> = MutableStateFlow(null)
    private val community: StateFlow<DomainCommunity?> = _community.asStateFlow()

    private val _isExpanded: MutableStateFlow<Boolean> = MutableStateFlow(false)
    private val isExpanded: StateFlow<Boolean> = _isExpanded.asStateFlow()

    init {
        getCommunityDetails(communityId)
    }

    fun getCommunityDetailsFlow(): StateFlow<DomainCommunity?> = community

    fun isExpandedFlow(): StateFlow<Boolean> = isExpanded

    fun getCommunityDetails(communityId: String) {
        viewModelScope.launch {
            iGetCommunityDetailsUseCase.execute(communityId).collect { communityDetails ->
                _community.update { communityDetails }
            }
        }
    }

    fun toggleExpanded() {
        _isExpanded.value = !_isExpanded.value
    }
}