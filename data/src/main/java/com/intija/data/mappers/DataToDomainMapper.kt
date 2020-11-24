package com.intija.data.mappers

interface DataToDomainMapper<R, E> {
    fun mapFromDataToDomain(model: R): E
}