package com.turitsynanton.android.wbtech.uinew.newviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turitsynanton.android.wbtech.domain.newmodels.DomainCommunity
import com.turitsynanton.android.wbtech.domain.newusecases.community.IGetCommunityDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ScreenCommunityDetailsViewModel(
    communityId: String,
    private val iGetCommunityDetailsUseCase: IGetCommunityDetailsUseCase
): ViewModel() {
    private val _community: MutableStateFlow<DomainCommunity?> = MutableStateFlow(null)
    private val community: StateFlow<DomainCommunity?> = _community.asStateFlow()

    init {
        getCommunityDetails(communityId)
    }

    fun getCommunityDetailsFlow(): StateFlow<DomainCommunity?> = community

    fun getCommunityDetails(communityId: String) {
        viewModelScope.launch {
            iGetCommunityDetailsUseCase.execute(communityId).collect { communityDetails ->
                _community.update { communityDetails }
            }
        }
    }
}