package com.turitsynanton.android.wbtech.data.repository.mapper

interface IMapperToDomain<in E, out D> {
    fun mapToDomain(entity: E): D
}