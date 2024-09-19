package com.turitsynanton.android.wbtech.data.repository.mappertodomain

import com.turitsynanton.android.wbtech.data.storage.models.DataHost
import com.turitsynanton.android.wbtech.domain.models.DomainHost

internal class HostMapperToDomain : IMapperToDomain<DataHost, DomainHost> {
    override fun mapToDomain(entity: DataHost): DomainHost {
        return DomainHost(
            id = entity.id,
            name = entity.name,
            description = entity.description,
            icon = entity.icon
        )
    }
}

internal fun DataHost.mapHostToDomain(mapper: IMapperToDomain<DataHost, DomainHost>): DomainHost {
    return mapper.mapToDomain(this)
}