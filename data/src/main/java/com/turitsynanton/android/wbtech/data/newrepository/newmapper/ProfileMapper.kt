package com.turitsynanton.android.wbtech.data.newrepository.newmapper

import com.turitsynanton.android.wbtech.data.storage.newmodels.DataProfile
import com.turitsynanton.android.wbtech.data.storage.newmodels.DataUser
import com.turitsynanton.android.wbtech.domain.newmodels.DomainProfile
import com.turitsynanton.android.wbtech.domain.newmodels.DomainUser

internal class ProfileMapper(
    private val tagsMapper: TagsMapper
): IMapper<DataProfile, DomainProfile> {
    override fun mapToDomain(entity: DataProfile): DomainProfile {
        return DomainProfile(
            id = entity.id,
            name = entity.name,
            city = entity.city,
            description = entity.description,
            tags = entity.tags.map { tag ->
                tagsMapper.mapToDomain(tag)
            },
            icon = entity.icon
        )
    }
}

internal fun DataProfile.mapProfileToDomain(mapper: IMapper<DataProfile, DomainProfile>): DomainProfile {
    return mapper.mapToDomain(this)
}

internal fun List<DataProfile>.mapProfileToDomain(mapper: IMapper<DataProfile, DomainProfile>): List<DomainProfile> {
    return map { it.mapProfileToDomain(mapper) }
}