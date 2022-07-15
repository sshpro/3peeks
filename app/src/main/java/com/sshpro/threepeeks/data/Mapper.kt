package com.sshpro.threepeeks.data

interface Mapper<Entity,  Domain> {
    fun mapToDomain(input:Entity): Domain
}