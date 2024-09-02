package com.turitsynanton.android.wbtech.models.mapper

interface IMapperToUi<in E, out D> {
    fun mapToUi(entity: E): D
}