package com.turitsynanton.android.wbtech.ui.screens.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turitsynanton.android.wbtech.domain.models.DomainCommunity
import com.turitsynanton.android.wbtech.domain.repository.CommunityRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CommunityViewModel(private val repository: CommunityRepository): ViewModel() {

    private val _Domain_communityList: MutableStateFlow<List<DomainCommunity>> = MutableStateFlow(emptyList())
    private val domainCommunityList: StateFlow<List<DomainCommunity>> = _Domain_communityList.asStateFlow()
    fun getMeetingsListFlow() = domainCommunityList

    init {
        getCommunityList()
    }

    fun getCommunityList() {
        viewModelScope.launch {
            repository.getCommunitiesList().collect { communityList ->
                _Domain_communityList.update { communityList }
            }
        }
    }
}