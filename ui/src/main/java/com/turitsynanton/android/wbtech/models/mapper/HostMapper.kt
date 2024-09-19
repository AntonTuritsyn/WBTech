package com.turitsynanton.android.wbtech.models.mapper

import com.turitsynanton.android.wbtech.domain.models.DomainHost
import com.turitsynanton.android.wbtech.domain.models.DomainUser
import com.turitsynanton.android.wbtech.models.UiHost

class HostMapper: IMapperToUi<DomainUser, UiHost> {
    override fun mapToUi(entity: DomainUser): UiHost {
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