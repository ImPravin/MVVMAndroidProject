package com.example.shaadiapp.domain.repository

import com.example.shaadiapp.domain.entities.PersonRequestEntity

interface PersonRequestRepository {
    suspend fun getAllRequestsFromServer()
    suspend fun getAllRequestsFromDatabase(): List<PersonRequestEntity>
    suspend fun updateRequestStatus(personRequest: PersonRequestEntity)
}