package com.turitsynanton.android.wbtech.data.repository.mappertodomain

import com.turitsynanton.android.wbtech.data.storage.models.DataVisitor
import com.turitsynanton.android.wbtech.domain.models.DomainVisitor

internal class VisitorMapperToDomain: IMapperToDomain<DataVisitor, DomainVisitor> {
    override fun mapToDomain(entity: DataVisitor): DomainVisitor {
        return DomainVisitor(
            id = entity.id,
            name = entity.name,
            phone = entity.phone
        )
    }
}