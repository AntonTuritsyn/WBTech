package com.turitsynanton.android.wbtech.models.mapper

import com.turitsynanton.android.wbtech.domain.newmodels.DomainTag
import com.turitsynanton.android.wbtech.models.UiTag

internal class TagMapper: IMapperToUi<DomainTag, UiTag> {
    override fun mapToUi(entity: DomainTag): UiTag {
        return UiTag(
            id = entity.id,
            content = entity.content
        )
    }
}

internal fun DomainTag.mapTagToUi(mapper: IMapperToUi<DomainTag, UiTag>): UiTag {
    return mapper.mapToUi(this)
}

internal fun List<DomainTag>.mapTagToUi(mapper: IMapperToUi<DomainTag, UiTag>): List<UiTag> {
    return map { it.mapTagToUi(mapper) }
}