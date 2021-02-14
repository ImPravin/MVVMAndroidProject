package com.example.shaadiapp.framework.database

import androidx.room.*
import com.example.shaadiapp.domain.entities.PersonRequestEntity

@Dao
interface PersonRequestDao {
    @Query("SELECT * FROM request_table")
    fun getAllRequests(): List<PersonRequestEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPersonRequest(personRequests: PersonRequestEntity)

    @Update
    fun updatePersonRequest(personRequest: PersonRequestEntity)
}