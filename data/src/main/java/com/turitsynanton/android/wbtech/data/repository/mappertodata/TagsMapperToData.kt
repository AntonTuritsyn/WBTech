package com.turitsynanton.android.wbtech.data.repository.mappertodata

import com.turitsynanton.android.wbtech.data.storage.models.DataTag
import com.turitsynanton.android.wbtech.domain.models.DomainTag

internal class TagsMapperToData: IMapperToData<DomainTag, DataTag> {
    override fun mapToData(entity: DomainTag): DataTag {
        return DataTag(
            id = entity.id,
            content = entity.content
        )
    }
}

internal fun DomainTag.mapTagToData(mapper: IMapperToData<DomainTag, DataTag>): DataTag {
    return mapper.mapToData(this)
}

internal fun List<DomainTag>.mapTagToData(mapper: IMapperToData<DomainTag, DataTag>): List<DataTag> {
    return map { it.mapTagToData(mapper) }
}