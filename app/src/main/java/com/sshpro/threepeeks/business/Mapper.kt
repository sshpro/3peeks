package com.sshpro.threepeeks.business

interface Mapper<Entity,  Domain> {
    fun mapToDomain(input:Entity): Domain
}