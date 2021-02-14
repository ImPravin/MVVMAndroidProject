package com.example.shaadiapp.domain.usecases

import com.example.shaadiapp.domain.entities.PersonRequestEntity
import com.example.shaadiapp.domain.repository.PersonRequestRepository

class PersonRequestUseCase(private val personRequestRepository: PersonRequestRepository) {

    suspend fun getAllRequestsFromServer() {
         personRequestRepository.getAllRequestsFromServer()
    }

    suspend fun getAllRequestFromDataBase(): List<PersonRequestEntity> {
        return personRequestRepository.getAllRequestsFromDatabase()
    }

    suspend fun updatePersonRequest(personRequest: PersonRequestEntity){
        personRequestRepository.updateRequestStatus(personRequest)
    }
}