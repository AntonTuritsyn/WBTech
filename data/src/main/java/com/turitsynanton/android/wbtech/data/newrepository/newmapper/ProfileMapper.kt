package com.turitsynanton.android.wbtech.data.newrepository.newmapper

import com.turitsynanton.android.wbtech.data.storage.newmodels.DataMyProfile
import com.turitsynanton.android.wbtech.domain.newmodels.DomainProfile

internal class ProfileMapper(
    private val tagsMapper: TagsMapper
): IMapper<DataMyProfile, DomainProfile> {
    override fun mapToDomain(entity: DataMyProfile): DomainProfile {
        return DomainProfile(
            id = entity.id,
            name = entity.name,
            city = entity.city,
            phone = entity.phone,
            description = entity.description,
            tags = entity.tags.map { tag ->
                tagsMapper.mapToDomain(tag)
            },
            icon = entity.icon
        )
    }
}

internal fun DataMyProfile.mapProfileToDomain(mapper: IMapper<DataMyProfile, DomainProfile>): DomainProfile {
    return mapper.mapToDomain(this)
}

internal fun List<DataMyProfile>.mapProfileToDomain(mapper: IMapper<DataMyProfile, DomainProfile>): List<DomainProfile> {
    return map { it.mapProfileToDomain(mapper) }
}