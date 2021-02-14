package com.example.shaadiapp.data.repositories.impl

import com.example.shaadiapp.data.datasources.PersonRequestDataSource
import com.example.shaadiapp.domain.entities.PersonRequestEntity
import com.example.shaadiapp.domain.repository.PersonRequestRepository

class PersonRequestRepositoryImpl(private val personRequestDataSource: PersonRequestDataSource) :
    PersonRequestRepository {
    override suspend fun getAllRequestsFromServer() {
        personRequestDataSource.getAllRequestsFromServer()
    }

    override suspend fun getAllRequestsFromDatabase(): List<PersonRequestEntity> {
        return personRequestDataSource.getAllRequestsFromDatabase()
    }

    override suspend fun updateRequestStatus(personRequest: PersonRequestEntity) {
        personRequestDataSource.updateRequestStatus(personRequest)
    }
}