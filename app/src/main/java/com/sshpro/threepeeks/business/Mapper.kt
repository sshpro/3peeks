package com.sshpro.threepeeks.business

interface Mapper<Entity1,Entity2,  Domain> {
    fun mapToDomain(input1: Entity1, input2: Entity2): Domain
    fun mapToDomainList(entities1: List<Entity1>, entities2: List<Entity2>): List<Domain>
}