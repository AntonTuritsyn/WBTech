package com.turitsynanton.android.wbtech.data.repository

import com.turitsynanton.android.wbtech.data.repository.mappertodomain.VisitorMapperToDomain
import com.turitsynanton.android.wbtech.domain.models.DomainVisitor
import com.turitsynanton.android.wbtech.domain.repository.VisitorRepository
import kotlinx.coroutines.flow.Flow

internal class VisitorRepositoryImpl(private val mapper: VisitorMapperToDomain): VisitorRepository {
    override fun saveVisitor(visitor: DomainVisitor): Flow<Boolean> {
        TODO("Not yet implemented")
    }
}