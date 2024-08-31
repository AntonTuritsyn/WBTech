package com.turitsynanton.android.wbtech.data.newrepository

import com.turitsynanton.android.wbtech.data.newrepository.newmapper.VisitorMapper
import com.turitsynanton.android.wbtech.domain.newmodels.DomainVisitor
import com.turitsynanton.android.wbtech.domain.newrepository.VisitorRepository
import kotlinx.coroutines.flow.Flow

internal class VisitorRepositoryImpl(private val mapper: VisitorMapper): VisitorRepository {
    override fun saveVisitor(visitor: DomainVisitor): Flow<Boolean> {
        TODO("Not yet implemented")
    }
}