package com.example.shaadiapp.data.datasources

import com.example.shaadiapp.domain.entities.PersonRequestEntity

interface PersonRequestDataSource {
    suspend fun getAllRequestsFromServer()
    suspend fun getAllRequestsFromDatabase() :List<PersonRequestEntity>
    suspend fun updateRequestStatus(personRequest: PersonRequestEntity)
    suspend fun insertRequests(personRequestEntities: List<PersonRequestEntity>)
}