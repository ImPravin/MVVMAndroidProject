package com.example.shaadiapp.framework.datasources.impl

import androidx.annotation.WorkerThread
import com.example.shaadiapp.data.datasources.PersonRequestDataSource
import com.example.shaadiapp.domain.entities.PersonRequestEntity
import com.example.shaadiapp.framework.database.PersonRequestDao
import com.example.shaadiapp.framework.network.ApiService

class PersonRequestDataSourceImpl(
    private val apiService: ApiService,
    private val personRequestDao: PersonRequestDao
) : PersonRequestDataSource {
    override suspend fun getAllRequestsFromServer() {
        val requestResult = apiService.getAllRequests(10)
        val personRequestEntities: List<PersonRequestEntity> = requestResult.results
        //TODO random user API all time returns random users so no data is repeating
        // here setting status as "2" : neither accepted nor declined
        for (personRequestEntity: PersonRequestEntity in personRequestEntities) {
            personRequestEntity.status = 2
        }
        insertRequests(personRequestEntities)
    }

    override suspend fun getAllRequestsFromDatabase() :List<PersonRequestEntity>{
        return personRequestDao.getAllRequests()
    }

    override suspend fun updateRequestStatus(personRequest: PersonRequestEntity) {
        personRequestDao.updatePersonRequest(personRequest)
    }


    @WorkerThread
    override suspend fun insertRequests(personRequestEntities: List<PersonRequestEntity>) {
        for (personRequestEntity: PersonRequestEntity in personRequestEntities)
            personRequestDao.insertPersonRequest(personRequestEntity)
    }
}