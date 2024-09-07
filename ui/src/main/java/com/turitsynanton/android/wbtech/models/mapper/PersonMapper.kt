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
            description = entity.description,
            tags = entity.tags.map { tag ->
                tagMapper.mapToUi(tag)
            },
            avatar = entity.icon
        )
    }
}