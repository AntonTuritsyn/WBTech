package com.turitsynanton.android.wbtech.domain.newrepository

import com.turitsynanton.android.wbtech.domain.newmodels.DomainVisitor
import kotlinx.coroutines.flow.Flow

interface VisitorRepository {
    fun saveVisitor(visitor: DomainVisitor): Flow<Boolean>
}