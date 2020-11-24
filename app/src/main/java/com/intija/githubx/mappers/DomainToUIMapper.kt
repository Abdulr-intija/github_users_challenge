package com.intija.githubx.mappers

interface DomainToUIMapper<R, E> {
    fun mapDomainToUI(model: R): E
}