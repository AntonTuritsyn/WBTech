package com.turitsynanton.android.wbtech.data.repository.mappertodomain

import com.turitsynanton.android.wbtech.data.storage.models.DataTag
import com.turitsynanton.android.wbtech.domain.models.DomainTag

internal class TagsMapperToDomain: IMapperToDomain<DataTag, DomainTag> {
    override fun mapToDomain(entity: DataTag): DomainTag {
        return DomainTag(
            id = entity.id,
            content = entity.content
        )
    }
}

internal fun DataTag.mapTagToDomain(mapper: IMapperToDomain<DataTag, DomainTag>): DomainTag {
    return mapper.mapToDomain(this)
}

internal fun List<DataTag>.mapTagToDomain(mapper: IMapperToDomain<DataTag, DomainTag>): List<DomainTag> {
    return map { it.mapTagToDomain(mapper) }
}