package com.turitsynanton.android.wbtech.data.newrepository.newmapper

import com.turitsynanton.android.wbtech.data.storage.newmodels.DataVisitor
import com.turitsynanton.android.wbtech.domain.newmodels.DomainVisitor

internal class VisitorMapper: IMapper<DataVisitor, DomainVisitor> {
    override fun mapToDomain(entity: DataVisitor): DomainVisitor {
        return DomainVisitor(
            id = entity.id,
            name = entity.name,
            phone = entity.phone
        )
    }
}