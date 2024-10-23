package com.turitsynanton.android.wbtech.ui.utils

import com.turitsynanton.android.ui.R

internal enum class TopBarStyles(val backIcon: Int = R.drawable.ic_back, val icon: Int) {
    Empty(icon = 0),
    Share(icon = R.drawable.ic_share),
    Edit(icon = R.drawable.ic_edit),
    Save(backIcon = R.drawable.ic_cross_small, icon = R.drawable.ic_approve)
}