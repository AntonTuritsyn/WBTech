package com.turitsynanton.android.wbtech.ui.utils

import android.content.Context

internal class ResourceProvider(private val context: Context) {
    fun getString(resId: Int): String {
        return context.getString(resId)
    }
}