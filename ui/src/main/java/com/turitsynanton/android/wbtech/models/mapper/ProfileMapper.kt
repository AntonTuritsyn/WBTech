package com.turitsynanton.android.wbtech.models.mapper

import com.turitsynanton.android.wbtech.domain.models.DomainProfile
import com.turitsynanton.android.wbtech.models.UiPerson

internal class ProfileMapper(
    private val tagMapper: TagMapper
): IMapperToUi<DomainProfile, UiPerson> {
    override fun mapToUi(entity: DomainProfile): UiPerson {
        return UiPerson(
            id = entity.id,
            name = entity.name,
            city = entity.city,
            phone = entity.phone,
            description = entity.description,
            tags = entity.tags.map { tag ->
                tagMapper.mapToUi(tag)
            },
            avatar = entity.icon
        )
    }
}

internal fun DomainProfile.mapPersonToUi(mapper: IMapperToUi<DomainProfile, UiPerson>): UiPerson {
    return mapper.mapToUi(this)
}

internal fun List<DomainProfile>.mapPersonToUi(mapper: IMapperToUi<DomainProfile, UiPerson>): List<UiPerson> {
    return map { it.mapPersonToUi(mapper) }
}