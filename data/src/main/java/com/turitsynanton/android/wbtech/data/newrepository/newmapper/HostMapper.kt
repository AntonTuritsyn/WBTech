package com.turitsynanton.android.wbtech.data.newrepository.newmapper

import com.turitsynanton.android.wbtech.data.storage.newmodels.DataHost
import com.turitsynanton.android.wbtech.domain.newmodels.DomainHost

internal class HostMapper : IMapper<DataHost, DomainHost> {
    override fun mapToDomain(entity: DataHost): DomainHost {
        return DomainHost(
            id = entity.id,
            name = entity.name,
            description = entity.description,
            icon = entity.icon
        )
    }
}