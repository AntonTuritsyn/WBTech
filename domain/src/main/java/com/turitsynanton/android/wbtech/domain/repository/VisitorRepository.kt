package com.turitsynanton.android.wbtech.domain.repository

import com.turitsynanton.android.wbtech.domain.models.DomainVisitor
import kotlinx.coroutines.flow.Flow

interface VisitorRepository {
    fun saveVisitor(visitor: DomainVisitor): Flow<Boolean>
}