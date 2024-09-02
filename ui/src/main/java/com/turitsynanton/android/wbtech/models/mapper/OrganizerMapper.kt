package com.turitsynanton.android.wbtech.models.mapper

import com.turitsynanton.android.wbtech.domain.newmodels.DomainCommunity
import com.turitsynanton.android.wbtech.models.UiOrganizer

class OrganizerMapper: IMapperToUi<DomainCommunity, UiOrganizer> {
    override fun mapToUi(entity: DomainCommunity): UiOrganizer {
        return UiOrganizer(
            id = entity.id,
            name = entity.name,
            description = entity.description,
            icon = entity.icon
        )
    }
}

internal fun DomainCommunity.mapOrganizerToUi(mapper: IMapperToUi<DomainCommunity, UiOrganizer>): UiOrganizer {
    return mapper.mapToUi(this)
}