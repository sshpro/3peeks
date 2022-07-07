package com.sshpro.threepeeks.domain.mappers

interface Mapper<Entity,  Domain> {
    fun mapToDomain(input:Entity): Domain
}