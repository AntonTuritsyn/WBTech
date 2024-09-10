package com.turitsynanton.android.wbtech.domain.newusecases.permissions

interface ICheckLocationPermissionUseCase {
    fun hasLocationPermission(): Boolean
    fun requestLocationPermission()
}