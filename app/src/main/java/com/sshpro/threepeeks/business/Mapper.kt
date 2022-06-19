package com.sshpro.threepeeks.business

interface Mapper<Entity1,Entity2,  Domain> {
    fun mapToDomain(input1: Entity1, input2: Entity2): Domain
}