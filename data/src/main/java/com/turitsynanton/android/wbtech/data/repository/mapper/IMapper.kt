package com.turitsynanton.android.wbtech.data.repository.mapper

interface IMapper<in E, out D> {
    fun mapToDomain(entity: E): D
}