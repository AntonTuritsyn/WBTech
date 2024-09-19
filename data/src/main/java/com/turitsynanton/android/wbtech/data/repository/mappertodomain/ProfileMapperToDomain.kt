package com.turitsynanton.android.wbtech.data.repository.mappertodomain

import com.turitsynanton.android.wbtech.data.storage.models.DataMyProfile
import com.turitsynanton.android.wbtech.domain.models.DomainProfile

internal class ProfileMapperToDomain(
    private val tagsMapper: TagsMapperToDomain
): IMapperToDomain<DataMyProfile, DomainProfile> {
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

internal fun DataMyProfile.mapProfileToDomain(mapper: IMapperToDomain<DataMyProfile, DomainProfile>): DomainProfile {
    return mapper.mapToDomain(this)
}

internal fun List<DataMyProfile>.mapProfileToDomain(mapper: IMapperToDomain<DataMyProfile, DomainProfile>): List<DomainProfile> {
    return map { it.mapProfileToDomain(mapper) }
}