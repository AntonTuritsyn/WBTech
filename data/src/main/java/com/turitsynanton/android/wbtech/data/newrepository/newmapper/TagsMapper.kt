package com.turitsynanton.android.wbtech.data.newrepository.newmapper

import com.turitsynanton.android.wbtech.data.storage.newmodels.DataTag
import com.turitsynanton.android.wbtech.domain.newmodels.DomainTag

internal class TagsMapper: IMapper<DataTag, DomainTag> {
    override fun mapToDomain(entity: DataTag): DomainTag {
        return DomainTag(
            id = entity.id,
            content = entity.content
        )
    }
}

internal fun DataTag.mapTagToDomain(mapper: IMapper<DataTag, DomainTag>): DomainTag {
    return mapper.mapToDomain(this)
}

internal fun List<DataTag>.mapTagToDomain(mapper: IMapper<DataTag, DomainTag>): List<DomainTag> {
    return map { it.mapTagToDomain(mapper) }
}