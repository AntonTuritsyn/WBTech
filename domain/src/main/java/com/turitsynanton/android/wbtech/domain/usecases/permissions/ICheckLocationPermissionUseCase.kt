package com.turitsynanton.android.wbtech.domain.usecases.permissions

interface ICheckLocationPermissionUseCase {
    fun hasLocationPermission(): Boolean
    fun requestLocationPermission()
}