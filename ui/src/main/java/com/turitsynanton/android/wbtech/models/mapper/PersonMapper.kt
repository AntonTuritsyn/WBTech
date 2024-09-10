package com.turitsynanton.android.wbtech.models.mapper

import com.turitsynanton.android.wbtech.data.repository.mapper.IMapperToDomain
import com.turitsynanton.android.wbtech.domain.newmodels.DomainUser
import com.turitsynanton.android.wbtech.models.UiPerson

internal class PersonMapper(
    private val tagMapper: TagMapper
): IMapperToUi<DomainUser, UiPerson> {
    override fun mapToUi(entity: DomainUser): UiPerson {
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

internal fun DomainUser.mapPersonToUi(mapper: IMapperToUi<DomainUser, UiPerson>): UiPerson {
    return mapper.mapToUi(this)
}

internal fun List<DomainUser>.mapPersonToUi(mapper: IMapperToUi<DomainUser, UiPerson>): List<UiPerson> {
    return map { it.mapPersonToUi(mapper) }
}