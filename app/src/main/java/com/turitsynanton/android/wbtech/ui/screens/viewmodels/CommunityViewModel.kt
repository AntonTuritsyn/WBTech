package com.turitsynanton.android.wbtech.ui.screens.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turitsynanton.android.wbtech.data.repository.CommunityRepositoryImpl
import com.turitsynanton.android.wbtech.domain.models.Community
import com.turitsynanton.android.wbtech.domain.repository.CommunityRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CommunityViewModel(private val repository: CommunityRepository): ViewModel() {

    private val _communityList: MutableStateFlow<List<Community>> = MutableStateFlow(emptyList())
    fun getMeetingsListFlow(): StateFlow<List<Community>> = _communityList.asStateFlow()

    init {
        getCommunityList()
    }

    fun getCommunityList() {
        viewModelScope.launch {
            repository.getCommunitiesList().collect { communityList ->
                _communityList.update { communityList }
            }
        }
    }
}