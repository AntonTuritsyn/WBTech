package com.turitsynanton.android.wbtech.ui.screens.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import com.turitsynanton.android.wbtech.domain.usecases.community.IGetCommunityListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CommunityViewModel(private val iGetCommunityListUseCase: IGetCommunityListUseCase) :
    ViewModel() {

    private val _communityList: MutableStateFlow<List<DomainCommunity>> =
        MutableStateFlow(emptyList())
    private val communityList: StateFlow<List<DomainCommunity>> = _communityList.asStateFlow()
    fun getMeetingsListFlow() = communityList

    init {
        getCommunityList()
    }

    fun getCommunityList() {
        viewModelScope.launch {
            iGetCommunityListUseCase.execute().collect { communityList ->
                _communityList.update { communityList }
            }
        }
    }
}