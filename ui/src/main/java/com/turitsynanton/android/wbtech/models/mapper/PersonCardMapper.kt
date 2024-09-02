package com.turitsynanton.android.wbtech.models.mapper

import com.turitsynanton.android.wbtech.domain.newmodels.DomainUser
import com.turitsynanton.android.wbtech.models.UiPerson

internal class PersonCardMapper(
    private val tagMapper: TagMapper
): IMapperToUi<DomainUser, UiPerson> {
    override fun mapToUi(entity: DomainUser): UiPerson {
        return UiPerson(
            id = entity.id,
            name = entity.name,
            tag = tagMapper.mapToUi(entity.tags.first())
        )
    }
}

internal fun DomainUser.mapPersonCardToUi(mapper: IMapperToUi<DomainUser, UiPerson>): UiPerson {
    return mapper.mapToUi(this)
}

internal fun List<DomainUser>.mapPersonCardToUi(mapper: IMapperToUi<DomainUser, UiPerson>): List<UiPerson> {
    return map { it.mapPersonCardToUi(mapper) }
}