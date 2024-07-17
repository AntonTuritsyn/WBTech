package com.turitsynanton.android.wbtech.ui.screens.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turitsynanton.android.wbtech.data.storage.models.Community
import com.turitsynanton.android.wbtech.domain.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CommunityViewModel(private val repository: Repository): ViewModel() {

    private val _communityList: MutableStateFlow<List<Community>> = MutableStateFlow(emptyList())
    fun getMeetingsListFlow(): StateFlow<List<Community>> = _communityList.asStateFlow()

    init {
        getCommunityList()
    }

    fun getCommunityList() {
        viewModelScope.launch {
            repository.getCommunityList().collect { communityList ->
                _communityList.update { communityList }
            }
        }
    }
}