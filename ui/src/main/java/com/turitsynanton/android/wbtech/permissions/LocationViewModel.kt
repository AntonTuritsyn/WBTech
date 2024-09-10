package com.turitsynanton.android.wbtech.permissions

import androidx.lifecycle.ViewModel
import com.turitsynanton.android.wbtech.domain.newusecases.permissions.ICheckLocationPermissionUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class LocationViewModel(
    private val locationPermissionChecker: AndroidPermissionChecker
): ViewModel() {

    private val _locationPermissionGranted = MutableStateFlow(false)
    private val locationPermissionGranted: StateFlow<Boolean> = _locationPermissionGranted

    fun locationPermissionGrantedFlow(): StateFlow<Boolean> = locationPermissionGranted

    fun checkLocationPermission() {
        _locationPermissionGranted.update { locationPermissionChecker.hasLocationPermission() }
    }

    fun requestLocationPermission() {
        locationPermissionChecker.requestLocationPermission()
    }
}