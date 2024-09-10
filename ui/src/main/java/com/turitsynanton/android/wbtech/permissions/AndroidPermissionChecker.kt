package com.turitsynanton.android.wbtech.permissions

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.turitsynanton.android.wbtech.domain.newusecases.permissions.ICheckLocationPermissionUseCase

private const val LOCATION_PERMISSION_REQUEST_CODE = 1001

class AndroidPermissionChecker(
    private val context: Context, private val activity: Activity
) {

    fun hasLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }
}