package com.turitsynanton.android.wbtech.models.mapper

import com.turitsynanton.android.wbtech.domain.newmodels.DomainHost
import com.turitsynanton.android.wbtech.models.UiHost

class HostMapper: IMapperToUi<DomainHost, UiHost> {
    override fun mapToUi(entity: DomainHost): UiHost {
        return UiHost(
            id = entity.id,
            name = entity.name,
            description = entity.description,
            icon = entity.icon
        )
    }
}

internal fun DomainHost.mapHostToUi(mapper: IMapperToUi<DomainHost, UiHost>): UiHost {
    return mapper.mapToUi(this)
}